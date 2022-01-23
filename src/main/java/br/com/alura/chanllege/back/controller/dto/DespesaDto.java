package br.com.alura.chanllege.back.controller.dto;

import java.time.LocalDate;

import br.com.alura.chanllege.back.modelo.Despesa;

public class DespesaDto {

	private Long id;
	private String descricao;
	private Double valor;
	private LocalDate data;
	
	public DespesaDto(Despesa despesa) {
		id = despesa.getId();
		descricao = despesa.getDescricao();
		valor = despesa.getValor();
		data = despesa.getData();
	}

	public Long getId() {
		return id;
	}

	public String getDescricao() {
		return descricao;
	}

	public Double getValor() {
		return valor;
	}

	public LocalDate getData() {
		return data;
	}
	
	

}
