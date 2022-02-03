package br.com.alura.challenge.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.alura.challenge.dto.ResumoDto;
import br.com.alura.challenge.repository.DespesaRepository;
import br.com.alura.challenge.repository.ReceitaRepository;

@Service
public class ResumoService {

	@Autowired
	ReceitaRepository receitaRepository;
	@Autowired
	DespesaRepository despesaRepository;
	
	public ResumoDto resumoPorMesAno(Integer ano, Integer mes) {
		
		ResumoDto resumo = new ResumoDto();
		String anoMes = "" + ano + mes;
		
		resumo.setTotalDasReceitas(receitaRepository.totalAnoMes(anoMes));
		System.out.println(resumo.getTotalDasReceitas());
		resumo.setTotalDasDespesas(despesaRepository.totalAnoMes(anoMes));
		System.out.println(resumo.getTotalDasDespesas());
		System.out.println(resumo.getSaldoMensal());
		resumo.setDespesasTotaisPorCategorias(despesaRepository.totalPorCategoriaNoAnoMes(anoMes));
		
		
		return resumo;
	}
}
