package br.com.alura.challenge.advice;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import br.com.alura.challenge.dto.ExceptionDto;
import br.com.alura.challenge.exceptions.EntidadeDuplicadaException;

@RestControllerAdvice
public class EntidadeDuplicadaExceptionHandler {

	@ExceptionHandler(EntidadeDuplicadaException.class)
	public ResponseEntity<ExceptionDto> handler(EntidadeDuplicadaException exception){
		ExceptionDto resposta = new ExceptionDto(HttpStatus.UNPROCESSABLE_ENTITY, exception.getMessage());
		
		return ResponseEntity.status(resposta.getStatus()).body(resposta);
	}
}
