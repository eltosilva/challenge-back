package br.com.alura.challenge.dto;

import java.time.LocalDate;

import javax.validation.constraints.Negative;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import br.com.alura.challenge.modelo.CategoriaDespesa;
import br.com.alura.challenge.modelo.Despesa;

public class DespesaDto {

	@NotNull
	@NotBlank
	@Size(min = 5)
	private String descricao;
	@NotNull
	@Negative
	private Double valor;
	@NotNull
	private LocalDate data;
	private CategoriaDespesa categoria;

	public DespesaDto() {
	}

	public CategoriaDespesa getCategoria() {
		return categoria;
	}

	public void setCategoria(CategoriaDespesa categoria) {
		this.categoria = categoria;
	}

	public DespesaDto(Despesa despesa) {
		descricao = despesa.getDescricao();
		valor = despesa.getValor();
		data = despesa.getData();
		categoria = despesa.getCategoria();
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

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public void setValor(Double valor) {
		this.valor = valor;
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
		
		if(categoria == null)
			despesa.setCategoria(CategoriaDespesa.OUTRAS);
		else
			despesa.setCategoria(categoria);

		return despesa;
	}

}
