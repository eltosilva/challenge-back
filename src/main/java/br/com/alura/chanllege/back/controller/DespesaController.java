package br.com.alura.chanllege.back.controller;

import javax.transaction.Transactional;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.alura.chanllege.back.controller.dto.DespesaDto;
import br.com.alura.chanllege.back.controller.dto.DespesaFormDto;
import br.com.alura.chanllege.back.modelo.Despesa;

@RestController
@RequestMapping
public class DespesaController {

	@PostMapping
	@Transactional
	public ResponseEntity<DespesaDto> cadastrar(DespesaFormDto formDto){
		
		Despesa despesa = new Despesa(formDto);
		return null;
	}
}
