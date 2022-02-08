package br.com.alura.challenge.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import br.com.alura.challenge.dto.DespesaDto;
import br.com.alura.challenge.modelo.CategoriaDespesa;
import br.com.alura.challenge.service.DespesaService;

@SpringBootTest
public class TestDespesaService {

	@Autowired
	DespesaService despesaService;

	@Test
	public void testConsultaPorId() {
		DespesaDto dd = new DespesaDto();
		dd.setDescricao("Financiamento Imobiliário");
		dd.setCategoria(CategoriaDespesa.MORADIA);
		dd.setValor(-700.0);
		dd.setData(LocalDate.of(2022, 1, 27));//2022-01-27
		
		DespesaDto despesaDto = despesaService.buscarPorId(1l);
		
		assertEquals(dd, despesaDto);
	}
	
	@Test
	public void testCadastrarNovaDespesa() {
		DespesaDto dd = new DespesaDto();
		dd.setDescricao("Supermercado");
		dd.setCategoria(CategoriaDespesa.ALIMENTACAO);
		dd.setValor(-600.0);
		dd.setData(LocalDate.now());
		
		DespesaDto dto = despesaService.salvar(dd);
		
		assertEquals(dd, dto);
	}
	
}
