package br.com.alura.challenge.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.alura.challenge.dto.DespesaDto;
import br.com.alura.challenge.exceptions.EntidadeDuplicadaException;
import br.com.alura.challenge.exceptions.NotFoundException;
import br.com.alura.challenge.modelo.Despesa;
import br.com.alura.challenge.repository.DespesaRepository;

@Service
public class DespesaService {

	@Autowired
	private DespesaRepository despesaRepository;

	public List<DespesaDto> listarDespesas(String descricao) {
		return despesaRepository.burcarPorDescricao(descricao).stream().map(DespesaDto::new).collect(Collectors.toList());
	}

	public DespesaDto buscarPorId(Long id) {
		Optional<Despesa> despesa = despesaRepository.findById(id);
		if (despesa.isEmpty())
			throw new NotFoundException("A despesa de id " + id + " não exite.");

		return new DespesaDto(despesa.get());
	}

	public List<DespesaDto> buscarPorAnoMes(Integer ano, Integer mes){
		List<Despesa> list = despesaRepository.findByAnoMes("" + ano + mes);
		
		if(list.isEmpty())
			throw new NotFoundException("Não foi encotrada despesas em " + mes + "/" + ano);
		
		return list.stream().map(DespesaDto::new).collect(Collectors.toList());
	}

	@Transactional
	public DespesaDto salvar(DespesaDto formDto) {

		Despesa despesa = formDto.criarDespesa();
		Optional<Despesa> consulta = despesaRepository.findByDescricaoAndAnoMes(despesa.getDescricao(),
				despesa.getAnoMes());

		if (consulta.isPresent())
			throw new EntidadeDuplicadaException("Já existe um despesa " + formDto.getDescricao() + " em " +
					formDto.getData().getMonthValue() + "/" + formDto.getData().getYear());

		despesa = despesaRepository.save(despesa);
		return new DespesaDto(despesa);
	}

	@Transactional
	public DespesaDto alterar(Long id, DespesaDto formDto) {
		Optional<Despesa> consultaAtual = despesaRepository.findById(id);
		if (consultaAtual.isEmpty())
			throw new NotFoundException("A despesa de id " + id + " não existe.");

		Optional<Despesa> consulta = despesaRepository.findByDescricaoAndAnoMes(formDto.getDescricao(),
				formDto.criarDespesa().getAnoMes());
		if (consulta.isPresent())
			throw new EntidadeDuplicadaException("Já existe um despesa " + formDto.getDescricao() + " em " +
					formDto.getData().getMonthValue() + "/" + formDto.getData().getYear());

		Despesa despesa = formDto.alterar(consultaAtual.get());
		return new DespesaDto(despesa);
	}

	@Transactional
	public DespesaDto remover(Long id) {
		Optional<Despesa> receita = despesaRepository.findById(id);
		if (receita.isEmpty())
			throw new NotFoundException("A despesa de id " + id + " não existe.");

		despesaRepository.delete(receita.get());
		return new DespesaDto(receita.get());
	}
}
