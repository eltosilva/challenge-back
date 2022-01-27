package br.com.alura.challenge.dto;

import java.time.LocalDate;

import javax.validation.constraints.Negative;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import br.com.alura.challenge.modelo.Despesa;

public class DespesaFormDto {

	private Long id;
	@NotNull @NotBlank @Size(min = 5)
	private String descricao;
	@NotNull @Negative
	private Double valor;
	@NotNull
	private LocalDate data;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Double getValor() {
		return valor;
	}

	public void setValor(Double valor) {
		this.valor = valor;
	}

	public LocalDate getData() {
		return data;
	}

	public void setData(LocalDate data) {
		this.data = data;
	}

	public Despesa criarDespesa() {
		Despesa despesa = new Despesa();
		
		return alterar(despesa);
	}

	public Despesa alterar(Despesa despesa) {
		despesa.setDescricao(descricao);
		despesa.setValor(valor);
		despesa.setData(data);
		despesa.setAnoMes("" + data.getYear() + data.getMonthValue());
		
		return despesa;
	}

}
