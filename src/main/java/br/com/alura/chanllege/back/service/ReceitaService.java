package br.com.alura.chanllege.back.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;

import br.com.alura.chanllege.back.controller.dto.ReceitaDto;
import br.com.alura.chanllege.back.controller.dto.ReceitaFormDto;
import br.com.alura.chanllege.back.modelo.Receita;
import br.com.alura.chanllege.back.repository.ReceitaRepository;

@Service
public class ReceitaService {

	@Autowired
	private ReceitaRepository receitaRepository;

	public List<ReceitaDto> listarReceitas() {
		return receitaRepository.findAll().stream().map(ReceitaDto::new).collect(Collectors.toList());
	}

	public ReceitaDto buscarPorId(Long id) {
		Optional<Receita> consulta = receitaRepository.findById(id);

		if (consulta.isEmpty())
			throw new HttpClientErrorException(HttpStatus.NOT_FOUND);

		return new ReceitaDto(consulta.get());
	}

	@Transactional
	public ReceitaDto salvar(ReceitaFormDto formDto) {

		Receita receita = formDto.criarReceita();
		Optional<Receita> consulta = receitaRepository.findByDescricaoAndAnoMes(receita.getDescricao(),	receita.getAnoMes());
		if (consulta.isPresent())
			throw new HttpClientErrorException(HttpStatus.UNPROCESSABLE_ENTITY);

		receita = receitaRepository.save(receita);
		return new ReceitaDto(receita);
	}

	@Transactional
	public ReceitaDto atualizar(ReceitaFormDto formDto) {
		Optional<Receita> consultaAtual = receitaRepository.findById(formDto.getId());
		if (consultaAtual.isEmpty())
			throw new HttpClientErrorException(HttpStatus.NOT_FOUND);

		Optional<Receita> consulta = receitaRepository.findByDescricaoAndAnoMes(formDto.getDescricao(),
				formDto.criarReceita().getAnoMes());
		if (consulta.isPresent())
			throw new HttpClientErrorException(HttpStatus.UNPROCESSABLE_ENTITY);

		Receita receita = formDto.alterar(consultaAtual.get());
		return new ReceitaDto(receita);
	}

	@Transactional
	public ReceitaDto remover(Long id) {
		Optional<Receita> receita = receitaRepository.findById(id);

		if (receita.isEmpty())
			throw new HttpClientErrorException(HttpStatus.NOT_FOUND);
		ReceitaDto receitaDto = new ReceitaDto(receita.get());

		receitaRepository.delete(receita.get());
		return receitaDto;
	}
}
