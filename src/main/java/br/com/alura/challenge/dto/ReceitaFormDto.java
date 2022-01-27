package br.com.alura.challenge.dto;

import java.time.LocalDate;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

import br.com.alura.challenge.modelo.Receita;

public class ReceitaFormDto {
	
	private Long id;
	@NotNull @NotBlank
	private String descricao;
	@NotNull @Positive
	private Double valor;
	@NotNull
	private LocalDate data;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public void setValor(Double valor) {
		this.valor = valor;
	}

	public void setData(LocalDate data) {
		this.data = data;
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

	public Receita alterar(Receita receita) {
		receita.setDescricao(descricao);
		receita.setValor(valor);
		receita.setData(data);
		receita.setAnoMes("" + data.getYear() + data.getMonthValue());
		
		return receita;
	}

	public Receita criarReceita() {
		Receita receita = new Receita();
		
		return alterar(receita);
	}

}
