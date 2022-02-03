package br.com.alura.challenge.dto;

import java.util.List;

public class ResumoDto {

	private Double totalReceitas;
	private Double totalDespesas;
	private List<ResumoCategoriaDto> despesasTotaisPorCategorias;

	public Double getTotalDasReceitas() {
		return totalReceitas;
	}

	public void setTotalDasReceitas(Double totalReceitas) {
		this.totalReceitas = totalReceitas;
	}

	public Double getTotalDasDespesas() {
		return totalDespesas;
	}

	public void setTotalDasDespesas(Double totalDespesas) {
		this.totalDespesas = totalDespesas;
	}

	public Double getSaldoMensal() {
		return totalReceitas + totalDespesas;
	}

	public List<ResumoCategoriaDto> getTotaisDespesasPorCategorias() {
		return despesasTotaisPorCategorias;
	}

	public void setDespesasTotaisPorCategorias(List<ResumoCategoriaDto> totaisCategorias) {
		this.despesasTotaisPorCategorias = totaisCategorias;
	}
}
