package br.com.alura.challenge.controller;

import static io.restassured.RestAssured.basePath;
import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.port;

import java.time.LocalDate;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import br.com.alura.challenge.dto.DespesaDto;
import br.com.alura.challenge.modelo.CategoriaDespesa;
import io.restassured.http.ContentType;

@SpringBootTest
public class TestDespesaController {

	@BeforeAll
	public static void configurarRestAssured() {
		
		baseURI = "http://localhost";
		basePath = "/despesas";
		port = 8080;
	}

	@Test
	public void buscaDespesaPeloAnoEMesComResultadoVazio() {		
		given()
			.contentType(ContentType.JSON)
			.pathParam("ano", 2021)
			.pathParam("mes", 12)
		.when()
			.get("/{ano}/{mes}")
		.then()
			.assertThat()
				.statusCode(404);
	}
	
	@Test
	public void salvarUmaDespesaDuplicada() {
		
		DespesaDto despesaDuplicada = new DespesaDto();
		despesaDuplicada.setCategoria(CategoriaDespesa.EDUCACAO);
		despesaDuplicada.setDescricao("Mensalidade Escolar");
		despesaDuplicada.setData(LocalDate.of(2022, 1, 15));
		despesaDuplicada.setValor(-300.0);
		
		given()
			.contentType(ContentType.JSON)
			.body(despesaDuplicada)
		.when()
			.post()
		.then()
			.assertThat()
				.statusCode(422);
	}
	
	
}
