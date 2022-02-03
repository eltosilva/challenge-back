package br.com.alura.challenge.controller;

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
import org.springframework.web.util.UriComponentsBuilder;

import br.com.alura.challenge.dto.ReceitaDto;
import br.com.alura.challenge.service.ReceitaService;

@RestController
@RequestMapping("/receitas")
public class ReceitaController {

	@Autowired
	private ReceitaService receitaService;

	@GetMapping
	public List<ReceitaDto> listar(String descricao) {
		return receitaService.listarReceitas(descricao);
	}

	@GetMapping("/{id}")
	public ResponseEntity<ReceitaDto> buscarPeloId(@PathVariable Long id) {
		ReceitaDto receita = receitaService.buscarPorId(id);
		return ResponseEntity.ok(receita);
	}
	
	@GetMapping("/{ano}/{mes}")
	public ResponseEntity<List<ReceitaDto>> buscarPorAnoMes(@PathVariable("ano") Integer ano, @PathVariable("mes") Integer mes){
		return ResponseEntity.ok(receitaService.buscarPoAnoMes(ano, mes));
	}

	@PostMapping
	public ResponseEntity<ReceitaDto> cadastrar(@RequestBody @Valid ReceitaDto formDto,
			UriComponentsBuilder uriBuilder) {

		ReceitaDto receita = receitaService.salvar(formDto);
		URI uri = uriBuilder.path("/receitas/{id}").buildAndExpand("?").toUri();

		return ResponseEntity.created(uri).body(receita);
	}

	@PutMapping("/{id}")
	public ResponseEntity<ReceitaDto> atualizar(@PathVariable Long id, @RequestBody @Valid ReceitaDto formDto) {
		ReceitaDto receita = receitaService.atualizar(id, formDto);
		return ResponseEntity.ok(receita);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<ReceitaDto> remover(@PathVariable Long id) {
		ReceitaDto receita = receitaService.remover(id);
		return ResponseEntity.ok(receita);
	}
}
