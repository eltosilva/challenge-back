package br.com.alura.challenge.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;

import br.com.alura.challenge.dto.ReceitaDto;
import br.com.alura.challenge.exceptions.EntidadeDuplicadaException;
import br.com.alura.challenge.exceptions.NotFoundException;
import br.com.alura.challenge.modelo.Receita;
import br.com.alura.challenge.repository.ReceitaRepository;

@Service
public class ReceitaService {

	@Autowired
	private ReceitaRepository receitaRepository;

	public List<ReceitaDto> listarReceitas(String descricao) {
		return receitaRepository.buscarPorDescricao(descricao).stream().map(ReceitaDto::new).collect(Collectors.toList());
	}

	public ReceitaDto buscarPorId(Long id) {
		Optional<Receita> consulta = receitaRepository.findById(id);

		if (consulta.isEmpty())
			throw new NotFoundException("Não encontrar a receita de id " + id + ".");

		return new ReceitaDto(consulta.get());
	}
	
	public List<ReceitaDto> buscarPoAnoMes(int ano, int mes){
		List<Receita> list = receitaRepository.findByAnoMes("" + ano + mes);
		
		if(list.isEmpty())
			throw new NotFoundException("Não encontrar a receitas no periodo "  + mes + "/" + ano + ".");
		
		return list.stream().map(ReceitaDto::new).collect(Collectors.toList());
	}

	@Transactional
	public ReceitaDto salvar(ReceitaDto formDto) {

		Receita receita = formDto.criarReceita();
		Optional<Receita> consulta = receitaRepository.findByDescricaoAndAnoMes(receita.getDescricao(),	receita.getAnoMes());
		if (consulta.isPresent())
			throw new EntidadeDuplicadaException("Já existe uma receita cadastrada com a descrição \"" + formDto.getDescricao() +
					"\" e periodo " + formDto.getData().getMonthValue() + "/" + formDto.getData().getYear());
		
		receita = receitaRepository.save(receita);
		return new ReceitaDto(receita);
	}

	@Transactional
	public ReceitaDto atualizar(Long id, ReceitaDto formDto) {
		Optional<Receita> consultaAtual = receitaRepository.findById(id);
		if (consultaAtual.isEmpty())
			throw new NotFoundException("Não encontrar a receita de id " + id + ".");

		Optional<Receita> consulta = receitaRepository.findByDescricaoAndAnoMes(formDto.getDescricao(),
				formDto.criarReceita().getAnoMes());
		if (consulta.isPresent())
			throw new EntidadeDuplicadaException("Já existe uma receita cadastrada com a descrição \"" + formDto.getDescricao() +
					"\" e periodo " + formDto.getData().getMonthValue() + "/" + formDto.getData().getYear());

		Receita receita = formDto.alterar(consultaAtual.get());
		return new ReceitaDto(receita);
	}

	@Transactional
	public ReceitaDto remover(Long id) {
		Optional<Receita> receita = receitaRepository.findById(id);

		if (receita.isEmpty())
			throw new NotFoundException("Não encontrar a receita de id " + id + ".");
		ReceitaDto receitaDto = new ReceitaDto(receita.get());

		receitaRepository.delete(receita.get());
		return receitaDto;
	}
}
