package br.com.alura.challenge.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import br.com.alura.challenge.modelo.Despesa;

@SpringBootTest
public class TestDespesaRepository {

	@Autowired
	private DespesaRepository despesaRepository;
	
	@Test
	public void testPesquisarPorDescricaoComSucesso() {
		List<Despesa> lista = despesaRepository.burcarPorDescricao("mensal");
		
		assertEquals(1, lista.size());
	}
	
	@Test
	public void testSomaDoTotalDoMes() {
		double soma = despesaRepository.totalAnoMes("20221");
		
		assertEquals(-1230.0, soma);
	}
}
