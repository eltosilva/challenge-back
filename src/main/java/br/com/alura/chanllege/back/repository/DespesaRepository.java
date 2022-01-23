package br.com.alura.chanllege.back.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.alura.chanllege.back.modelo.Despesa;

public interface DespesaRepository extends JpaRepository<Despesa, Long> {

	Optional<Despesa> findByDescricaoAndAnoMes(String descricao, String anoMes);
}
