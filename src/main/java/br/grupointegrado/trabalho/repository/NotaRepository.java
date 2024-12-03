package br.grupointegrado.trabalho.repository;

import br.grupointegrado.trabalho.model.Nota;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NotaRepository extends JpaRepository<Nota, Integer> {}
