package br.com.alura.challenge.dto;

import org.springframework.http.HttpStatus;

public class ExceptionDto {

	private HttpStatus status;
	private String mensagem;

	public ExceptionDto(HttpStatus status, String mensagem) {
		this.status = status;
		this.mensagem = mensagem;
	}

	public HttpStatus getStatus() {
		return status;
	}

	public String getMensagem() {
		return mensagem;
	}

}
