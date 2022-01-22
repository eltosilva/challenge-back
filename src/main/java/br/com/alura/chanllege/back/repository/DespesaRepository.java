package br.com.alura.chanllege.back.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.alura.chanllege.back.modelo.Despesa;

public interface DespesaRepository extends JpaRepository<Despesa, Long> {

}
