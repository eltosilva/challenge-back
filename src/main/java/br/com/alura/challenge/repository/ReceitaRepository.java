package br.com.alura.challenge.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.alura.challenge.modelo.Receita;

public interface ReceitaRepository extends JpaRepository<Receita, Long> {
	
	public Optional<Receita> findByDescricaoAndAnoMes(String descricao, String anoMes);

}
