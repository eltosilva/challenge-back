package br.com.alura.challenge.controller;

import static io.restassured.RestAssured.basePath;
import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.port;

import java.time.LocalDate;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import br.com.alura.challenge.dto.ReceitaDto;
import io.restassured.http.ContentType;

class ReceitaControllerTest {

	@BeforeAll
	public static void configurarResAssured() {
		baseURI = "http://localhost";
		port = 8080;
		basePath = "/receitas";
	}
	
	@Test
	public void buscaReceitaNaoEncontradaExpectativaDeStatusCode400NotFound() {
		given()
			.contentType(ContentType.JSON)
			.pathParam("id", 999)
		.when()
			.get("/{id}")
		.then()
			.assertThat()
				.statusCode(404);
	}
	
	@Test
	public void cadastrarReceitaDuplicadaExpectativaDeStatuCode422UnprocessableEntity() {
		ReceitaDto receitaDuplicada = new ReceitaDto();
		receitaDuplicada.setDescricao("Salário");
		receitaDuplicada.setData(LocalDate.of(2022, 1, 3));
		receitaDuplicada.setValor(5.0);
		
		given()
			.contentType(ContentType.JSON)
			.body(receitaDuplicada)
		.when()
			.post()
		.then()
			.assertThat()
				.statusCode(422);
	}

}
