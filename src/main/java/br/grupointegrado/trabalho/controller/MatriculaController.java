package br.grupointegrado.trabalho.controller;

import br.grupointegrado.trabalho.dto.MatriculaRequestDTO;
import br.grupointegrado.trabalho.model.*;
import br.grupointegrado.trabalho.repository.AlunoRepository;
import br.grupointegrado.trabalho.repository.MatriculaRepository;
import br.grupointegrado.trabalho.repository.TurmaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/matriculas")
public class MatriculaController {

    @Autowired
    private MatriculaRepository matriculaRepository;

    @Autowired
    private AlunoRepository alunoRepository;

    @Autowired
    private TurmaRepository turmaRepository;

    @GetMapping
    public ResponseEntity<List<Matricula>> findAll(){
        List<Matricula> matriculas = matriculaRepository.findAll();
        return ResponseEntity.ok(matriculas);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Matricula> findById(@PathVariable Integer id){
        Matricula matricula = matriculaRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Matricula não encontrada"));
        return ResponseEntity.ok(matricula);
    }

    @PostMapping
    public ResponseEntity<Matricula> save(@RequestBody MatriculaRequestDTO dto){
        Matricula matricula = new Matricula();

        Aluno aluno = alunoRepository.findById(dto.alunoId()).orElseThrow(() -> new IllegalArgumentException("Aluno não encontrado"));
        matricula.setAluno(aluno);

        Turma turma = turmaRepository.findById(dto.turmaId()).orElseThrow(() -> new IllegalArgumentException("Turma não encontrada"));
        matricula.setTurma(turma);

        return ResponseEntity.ok(matriculaRepository.save(matricula));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id){
        Matricula matricula = matriculaRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Matricula não encontrada"));
        matriculaRepository.delete(matricula);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Matricula> update(@PathVariable Integer id,@RequestBody MatriculaRequestDTO dto){
        Matricula matricula = matriculaRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Matricula não encontrada"));

        Aluno aluno = alunoRepository.findById(dto.alunoId()).orElseThrow(() -> new IllegalArgumentException("Aluno não encontrado"));
        matricula.setAluno(aluno);

        Turma turma = turmaRepository.findById(dto.turmaId()).orElseThrow(() -> new IllegalArgumentException("Turma não encontrada"));
        matricula.setTurma(turma);

        return ResponseEntity.ok(matriculaRepository.save(matricula));
    }

}
