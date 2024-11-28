package br.grupointegrado.trabalho.repository;

import br.grupointegrado.trabalho.model.Curso;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CursoRepository extends JpaRepository<Curso, Integer> { }
