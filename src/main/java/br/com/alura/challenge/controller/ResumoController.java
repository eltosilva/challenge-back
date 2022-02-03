package br.com.alura.challenge.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.alura.challenge.dto.ResumoDto;
import br.com.alura.challenge.service.ResumoService;

@RestController
@RequestMapping("/resumo")
public class ResumoController {

	@Autowired
	ResumoService resumoService;
	
	@GetMapping("/{ano}/{mes}")
	public ResponseEntity<ResumoDto> resumoPorAnoMes(@PathVariable Integer ano, @PathVariable Integer mes){
		return ResponseEntity.ok(resumoService.resumoPorMesAno(ano, mes));
	}
}
