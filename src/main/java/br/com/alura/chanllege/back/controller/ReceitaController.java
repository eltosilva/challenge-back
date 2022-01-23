package br.com.alura.chanllege.back.controller;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.UnexpectedRollbackException;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.alura.chanllege.back.controller.dto.ReceitaDto;
import br.com.alura.chanllege.back.controller.dto.ReceitaFormDto;
import br.com.alura.chanllege.back.modelo.Receita;
import br.com.alura.chanllege.back.repository.ReceitaRepository;
import br.com.alura.chanllege.back.service.ReceitaService;

@RestController
@RequestMapping("/receitas")
public class ReceitaController {

	@Autowired
	private ReceitaService receitaService;

	@GetMapping
	public List<ReceitaDto> listar(){
		return receitaService.listarReceitas();
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<ReceitaDto> buscarPeloId(@PathVariable Long id){
		try {
			ReceitaDto receita = receitaService.buscarPorId(id);
			return ResponseEntity.ok(receita);
		}catch(HttpClientErrorException httpException) {
			return ResponseEntity.notFound().build();			
		}
	}
	
	@PostMapping
	public ResponseEntity<ReceitaDto> cadastrar(@RequestBody @Valid ReceitaFormDto formDto,	UriComponentsBuilder uriBuilder) {
		
		try {			
			ReceitaDto receita = receitaService.salvar(formDto);			
			URI uri = uriBuilder.path("/receitas/{id}").buildAndExpand(receita.getId()).toUri();
			
			return ResponseEntity.created(uri).body(receita);
		}catch(HttpClientErrorException httpException) {
			return ResponseEntity.status(httpException.getStatusCode()).build();
		}
	}
	
	@PutMapping
	public ResponseEntity<ReceitaDto> atualizar(@RequestBody @Valid ReceitaFormDto formDto){
		try {
			ReceitaDto receita = receitaService.atualizar(formDto);
			return ResponseEntity.ok(receita);
		}catch(HttpClientErrorException httpExcepetion) {
			return ResponseEntity.status(httpExcepetion.getStatusCode()).build();
		}
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<ReceitaDto> remover(@PathVariable Long id){
		try {
			ReceitaDto receita = receitaService.remover(id);
			return ResponseEntity.ok(receita);
		}catch(HttpClientErrorException httpException) {
			return ResponseEntity.status(httpException.getStatusCode()).build();
		}
	}
}
