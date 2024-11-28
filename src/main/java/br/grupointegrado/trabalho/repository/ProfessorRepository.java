package br.grupointegrado.trabalho.repository;

import br.grupointegrado.trabalho.model.Professor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProfessorRepository extends JpaRepository<Professor, Integer> { }
