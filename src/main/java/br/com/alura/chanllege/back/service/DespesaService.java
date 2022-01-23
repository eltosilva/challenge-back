package br.com.alura.chanllege.back.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;

import br.com.alura.chanllege.back.controller.dto.DespesaDto;
import br.com.alura.chanllege.back.controller.dto.DespesaFormDto;
import br.com.alura.chanllege.back.modelo.Despesa;
import br.com.alura.chanllege.back.repository.DespesaRepository;

@Service
public class DespesaService {

	@Autowired
	private DespesaRepository despesaRepository;
	
	public List<DespesaDto> listarDespesas(){
		return despesaRepository.findAll().stream().map(DespesaDto::new).collect(Collectors.toList());
	}
	
	public DespesaDto buscarPorId(Long id) {
		Optional<Despesa> despesa = despesaRepository.findById(id);
		if(despesa.isEmpty())
			throw new HttpClientErrorException(HttpStatus.NOT_FOUND);
		
		return new DespesaDto(despesa.get());
	}
	
	@Transactional
	public DespesaDto salvar(DespesaFormDto formDto) {
		
		Despesa despesa = formDto.criarDespesa();
		Optional<Despesa> consulta = despesaRepository.findByDescricaoAndAnoMes(despesa.getDescricao(), despesa.getAnoMes());
		
		if(consulta.isPresent())
			throw new HttpClientErrorException(HttpStatus.UNPROCESSABLE_ENTITY);
		
		despesa = despesaRepository.save(despesa);
		return new DespesaDto(despesa);
	}
	
	@Transactional
	public DespesaDto alterar(DespesaFormDto formDto) {
		Optional<Despesa> consultaAtual = despesaRepository.findById(formDto.getId());
		if(consultaAtual.isEmpty())
			throw new HttpClientErrorException(HttpStatus.NOT_FOUND);
		
		Optional<Despesa> consulta = despesaRepository.findByDescricaoAndAnoMes(formDto.getDescricao(), formDto.criarDespesa().getAnoMes());
		if(consulta.isPresent())
			throw new HttpClientErrorException(HttpStatus.UNPROCESSABLE_ENTITY);
		
		Despesa despesa = formDto.alterar(consultaAtual.get());
		return new DespesaDto(despesa);
	}
	
	@Transactional
	public DespesaDto remover(Long id) {
		Optional<Despesa> receita = despesaRepository.findById(id);
		if(receita.isEmpty())
			throw new HttpClientErrorException(HttpStatus.NOT_FOUND);
		
		despesaRepository.delete(receita.get());
		return new DespesaDto(receita.get());
	}
}
