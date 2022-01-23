package br.com.alura.chanllege.back.controller;

import java.net.URI;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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

import br.com.alura.chanllege.back.controller.dto.DespesaDto;
import br.com.alura.chanllege.back.controller.dto.DespesaFormDto;
import br.com.alura.chanllege.back.service.DespesaService;

@RestController
@RequestMapping("/despesas")
public class DespesaController {

	@Autowired
	private DespesaService despesaService;
	
	@GetMapping
	public List<DespesaDto> listar() {
		return despesaService.listarDespesas();
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<DespesaDto> buscarPorId(@PathVariable Long id) {
		try {
			DespesaDto despesa = despesaService.buscarPorId(id);
			return ResponseEntity.ok(despesa);
		}catch(HttpClientErrorException httpException) {
			return ResponseEntity.status(httpException.getStatusCode()).build();
		}
	}
	
	@PostMapping
	public ResponseEntity<DespesaDto> cadastrar(@RequestBody @Valid DespesaFormDto formDto, UriComponentsBuilder uriBuilder){
		try {
			DespesaDto despesa = despesaService.salvar(formDto);
			URI uri = uriBuilder.path("/despesas/{id}").buildAndExpand(despesa.getId()).toUri();
			
			return ResponseEntity.created(uri).body(despesa);
		}catch(HttpClientErrorException httpException) {
			return ResponseEntity.status(httpException.getStatusCode()).build();
		}
	}
	
	@PutMapping
	public ResponseEntity<DespesaDto> atualizar(@RequestBody @Valid DespesaFormDto formDto){
		try {
			DespesaDto despesa = despesaService.alterar(formDto);
			return ResponseEntity.ok(despesa);
		}catch(HttpClientErrorException httpException) {
			return ResponseEntity.status(httpException.getStatusCode()).build();
		}
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<DespesaDto> removerDespesa(@PathVariable Long id){
		try {
			DespesaDto despesa = despesaService.remover(id);
			return ResponseEntity.ok(despesa);
		}catch(HttpClientErrorException httpException) {
			return ResponseEntity.status(httpException.getStatusCode()).build();
		}
	}
}
