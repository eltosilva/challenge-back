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

import br.com.alura.challenge.dto.DespesaDto;
import br.com.alura.challenge.service.DespesaService;

@RestController
@RequestMapping("/despesas")
public class DespesaController {

	@Autowired
	private DespesaService despesaService;

	@GetMapping
	public List<DespesaDto> listar(String descricao) {
		return despesaService.listarDespesas(descricao);
	}

	@GetMapping("/{id}")
	public ResponseEntity<DespesaDto> buscarPorId(@PathVariable Long id) {
		DespesaDto despesa = despesaService.buscarPorId(id);

		return ResponseEntity.ok(despesa);
	}
	
	@GetMapping("/{ano}/{mes}")
	public ResponseEntity<List<DespesaDto>> buscarPorAnoMes(@PathVariable("ano") Integer ano, @PathVariable("mes") Integer mes){
		return ResponseEntity.ok(despesaService.buscarPorAnoMes(ano, mes));
	}

	@PostMapping
	public ResponseEntity<DespesaDto> cadastrar(@RequestBody @Valid DespesaDto formDto,
			UriComponentsBuilder uriBuilder) {
		DespesaDto despesa = despesaService.salvar(formDto);
		URI uri = uriBuilder.path("/despesas/{id}").buildAndExpand("?").toUri();

		return ResponseEntity.created(uri).body(despesa);
	}

	@PutMapping("/{id}")
	public ResponseEntity<DespesaDto> atualizar(@PathVariable Long id, @RequestBody @Valid DespesaDto formDto) {
		DespesaDto despesa = despesaService.alterar(id, formDto);

		return ResponseEntity.ok(despesa);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<DespesaDto> removerDespesa(@PathVariable Long id) {
		DespesaDto despesa = despesaService.remover(id);

		return ResponseEntity.ok(despesa);
	}
}
