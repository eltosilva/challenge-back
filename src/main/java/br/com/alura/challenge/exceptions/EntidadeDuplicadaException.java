package br.com.alura.challenge.exceptions;

public class EntidadeDuplicadaException extends RuntimeException {

	private static final long serialVersionUID = -7523208964634305865L;

	public EntidadeDuplicadaException() {
		super();
	}
	
	public EntidadeDuplicadaException(String mensagem) {
		super(mensagem);
	}
}
