package br.com.alura.challenge.exceptions;

public class NotFoundException extends RuntimeException {

	private static final long serialVersionUID = 4758553275614153180L;

	public NotFoundException() {
		super();
	}
	
	public NotFoundException(String mensagem) {
		super(mensagem);
	}
}
