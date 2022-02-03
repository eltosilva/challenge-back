package br.com.alura.challenge.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import br.com.alura.challenge.dto.ResumoCategoriaDto;
import br.com.alura.challenge.modelo.Despesa;

public interface DespesaRepository extends JpaRepository<Despesa, Long> {

	public Optional<Despesa> findByDescricaoAndAnoMes(String descricao, String anoMes);
	
	public List<Despesa> findByAnoMes(String anoMes);
	
	@Query("select d from Despesa d where upper(d.descricao) like upper(concat('%', :descricao, '%'))")
	public List<Despesa> burcarPorDescricao(String descricao);
	
	@Query("select sum(d.valor) from Despesa d where d.anoMes = :anoMes")
	public Double totalAnoMes(String anoMes);
	
	@Query("select d.categoria as categoria, sum(d.valor) as total from Despesa d where d.anoMes = :anoMes group by d.categoria")
	public List<ResumoCategoriaDto> totalPorCategoriaNoAnoMes(String anoMes);
}
