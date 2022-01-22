package br.com.alura.chanllege.back.repository;

import java.time.LocalDate;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import br.com.alura.chanllege.back.modelo.Receita;

public interface ReceitaRepository extends JpaRepository<Receita, Long> {
	
	@Query("SELECT r FROM Receita r WHERE r.descricao = :descricao AND MONTH(r.data) = MONTH(:data) AND YEAR(r.data) = YEAR(:data)")
	public Optional<Receita> buscarDescricaoNoMesmoMes(String descricao, LocalDate data);

}
