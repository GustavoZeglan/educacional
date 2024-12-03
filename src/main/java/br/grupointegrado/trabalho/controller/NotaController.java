package br.grupointegrado.trabalho.controller;
import br.grupointegrado.trabalho.dto.NotaRequestDTO;
import br.grupointegrado.trabalho.model.Disciplina;
import br.grupointegrado.trabalho.model.Matricula;
import br.grupointegrado.trabalho.model.Nota;
import br.grupointegrado.trabalho.repository.DisciplinaRepository;
import br.grupointegrado.trabalho.repository.MatriculaRepository;
import br.grupointegrado.trabalho.repository.NotaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/notas")
public class NotaController {

    @Autowired
    private NotaRepository notaRepository;

    @Autowired
    private MatriculaRepository matriculaRepository;

    @Autowired
    private DisciplinaRepository disciplinaRepository;

    @GetMapping
    public ResponseEntity<List<Nota>> findAll(){
        return ResponseEntity.ok(notaRepository.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Nota> findById(@PathVariable Integer id){
        Nota nota = notaRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Nota não encontrada"));
        return ResponseEntity.ok(nota);
    }

    @PostMapping
    public ResponseEntity<Nota> save(@RequestBody NotaRequestDTO dto){
        Nota nota = new Nota();
        nota.setNota(dto.nota());
        nota.setDataLancamento(dto.dataLancamento());

        Matricula matricula = matriculaRepository.findById(dto.matriculaId()).orElseThrow(() -> new IllegalArgumentException("Matricula não encontrada"));
        Disciplina disciplina = disciplinaRepository.findById(dto.disciplinaId()).orElseThrow(() -> new IllegalArgumentException("Disciplina não encontrada"));

        nota.setDisciplina(disciplina);
        nota.setMatricula(matricula);

        return ResponseEntity.ok(notaRepository.save(nota));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id){
        Nota nota = notaRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Nota não encontrada"));
        notaRepository.delete(nota);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Nota> update(
            @PathVariable Integer id,
            @RequestBody NotaRequestDTO dto
    ){
        Nota nota = notaRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Nota não encontrada"));
        nota.setNota(dto.nota());
        nota.setDataLancamento(dto.dataLancamento());

        Matricula matricula = matriculaRepository.findById(dto.matriculaId()).orElseThrow(() -> new IllegalArgumentException("Matricula não encontrada"));
        Disciplina disciplina = disciplinaRepository.findById(dto.disciplinaId()).orElseThrow(() -> new IllegalArgumentException("Disciplina não encontrada"));

        nota.setDisciplina(disciplina);
        nota.setMatricula(matricula);

        return ResponseEntity.ok(notaRepository.save(nota));
    }
}