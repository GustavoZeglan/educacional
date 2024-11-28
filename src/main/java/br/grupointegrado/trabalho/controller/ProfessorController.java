package br.grupointegrado.trabalho.controller;
import br.grupointegrado.trabalho.dto.ProfessorRequestDTO;
import br.grupointegrado.trabalho.model.Professor;
import br.grupointegrado.trabalho.repository.ProfessorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController()
@RequestMapping("/api/professores")
public class ProfessorController {

    @Autowired
    ProfessorRepository professorRepository;

    @PostMapping()
    public Professor save(@RequestBody ProfessorRequestDTO dto) {
        Professor professor = new Professor();
        professor.setNome(dto.nome());
        professor.setEmail(dto.email());
        professor.setTelefone(dto.telefone());
        professor.setEspecialidade(dto.especialidade());

        return professorRepository.save(professor);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Professor> findById(@PathVariable() Integer id) {
        Professor professor = this.professorRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Professor não encontrado"));

        return ResponseEntity.ok(professor);
    }

    @GetMapping()
    public ResponseEntity<List<Professor>> findAll() {
        List<Professor> professors = this.professorRepository.findAll();
        return ResponseEntity.ok(professors);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Professor> update(@RequestBody ProfessorRequestDTO dto, @PathVariable Integer id) {

        Professor professor = this.professorRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Professor não encontrado"));

        professor.setNome(dto.nome());
        professor.setEmail(dto.email());
        professor.setTelefone(dto.telefone());
        professor.setEspecialidade(dto.especialidade());

        return ResponseEntity.ok(professorRepository.save(professor));
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Integer id) {
        Professor professor = this.professorRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Professor não encontrado"));

        this.professorRepository.delete(professor);
    }
}
