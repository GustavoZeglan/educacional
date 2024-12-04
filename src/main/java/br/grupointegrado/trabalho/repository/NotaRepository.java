package br.grupointegrado.trabalho.repository;

import br.grupointegrado.trabalho.model.Disciplina;
import br.grupointegrado.trabalho.model.Nota;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface NotaRepository extends JpaRepository<Nota, Integer> {
    List<Nota> findByDisciplina(Disciplina disciplina);
}
