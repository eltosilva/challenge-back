package br.com.alura.chanllege.back.controller;

import java.net.URI;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.alura.chanllege.back.controller.dto.ReceitaDto;
import br.com.alura.chanllege.back.controller.dto.ReceitaFormDto;
import br.com.alura.chanllege.back.modelo.Receita;
import br.com.alura.chanllege.back.repository.ReceitaRepository;

@RestController
@RequestMapping("/receitas")
public class ReceitaController {

	@Autowired
	private ReceitaRepository receitaRepository;

	@GetMapping
	public List<ReceitaDto> listar(){
		return receitaRepository.findAll().stream().map(ReceitaDto::new).collect(Collectors.toList());
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<ReceitaDto> buscarPeloId(@PathVariable Long id){
		Optional<Receita> consulta = receitaRepository.findById(id);
		
		if(consulta.isPresent())
			return ResponseEntity.ok(new ReceitaDto(consulta.get()));
		
		return ResponseEntity.notFound().build();
	}
	
	@PostMapping
	@Transactional
	public ResponseEntity<ReceitaDto> cadastrar(@RequestBody @Valid ReceitaFormDto formDto,	UriComponentsBuilder uriBuilder) {		
		Optional<Receita> consulta = receitaRepository.buscarDescricaoNoMesmoMes(formDto.getDescricao(), formDto.getData());

		if(consulta.isPresent())
			return ResponseEntity.status(HttpStatus.IM_USED).build();
		
		Receita receita = receitaRepository.save(new Receita(formDto));			
		URI uri = uriBuilder.path("/receitas/{id}").buildAndExpand(receita.getId()).toUri();
		
		return ResponseEntity.created(uri).body(new ReceitaDto(receita));
	}
	
	@PutMapping
	@Transactional
	public ResponseEntity<ReceitaDto> atualizar(@RequestBody @Valid ReceitaFormDto formDto){
		Optional<Receita> consultaAtual = receitaRepository.findById(formDto.getId());
		if(consultaAtual.isEmpty())
			return ResponseEntity.notFound().build();
		
		Optional<Receita> consultaDescricao = receitaRepository.buscarDescricaoNoMesmoMes(formDto.getDescricao(), formDto.getData());
		if(consultaDescricao.isPresent())
			return ResponseEntity.status(HttpStatus.IM_USED).build();
		
		formDto.atualizarReceita(consultaAtual.get());
		
		return ResponseEntity.ok(new ReceitaDto(consultaAtual.get()));
	}
	
	@DeleteMapping("/{id}")
	@Transactional
	public ResponseEntity<ReceitaDto> remover(@PathVariable Long id){
		Optional<Receita> consulta = receitaRepository.findById(id);
		
		if(consulta.isEmpty())
			return ResponseEntity.notFound().build();
		
		receitaRepository.delete(consulta.get());
		return ResponseEntity.ok(new ReceitaDto(consulta.get()));
	}
}
