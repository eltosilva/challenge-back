package br.com.alura.challenge.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import br.com.alura.challenge.modelo.Receita;

public interface ReceitaRepository extends JpaRepository<Receita, Long> {
	
	public Optional<Receita> findByDescricaoAndAnoMes(String descricao, String anoMes);
	
	public List<Receita> findByAnoMes(String anoMes);
	
	@Query("SELECT r FROM Receita r WHERE :descricao IS NULL OR upper(r.descricao) like upper(concat('%', :descricao, '%'))")
	public List<Receita> buscarPorDescricao(String descricao);
	
	@Query("select sum(r.valor) from Receita r where r.anoMes = :anoMes")
	public Double totalAnoMes(String anoMes);
	
	

}
