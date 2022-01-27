package br.com.alura.challenge.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.alura.challenge.modelo.Despesa;

public interface DespesaRepository extends JpaRepository<Despesa, Long> {

	Optional<Despesa> findByDescricaoAndAnoMes(String descricao, String anoMes);
}
