package br.com.alura.chanllege.back.controller.dto;

import java.time.LocalDate;
import java.util.Optional;

import br.com.alura.chanllege.back.modelo.Receita;

public class ReceitaDto {

	private Long id;
	private String descricao;
	private Double valor;
	private LocalDate data;

	public ReceitaDto(Receita receita) {
		this.id = receita.getId();
		this.descricao = receita.getDescricao();
		this.valor = receita.getValor();
		this.data = receita.getData();
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
