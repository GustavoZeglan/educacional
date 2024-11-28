package br.grupointegrado.trabalho.repository;

import br.grupointegrado.trabalho.model.Aluno;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AlunoRepository extends JpaRepository<Aluno, Integer> {}