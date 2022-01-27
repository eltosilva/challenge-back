package br.com.alura.challenge.advice;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.client.HttpClientErrorException;

@ControllerAdvice
public class HandlerHttpClientException {

	@ExceptionHandler(HttpClientErrorException.class)
	public ResponseEntity<String> handler(HttpClientErrorException exception){
		
		return ResponseEntity.status(exception.getStatusCode()).body(exception.getMessage());	
	}
}
