package br.grupointegrado.trabalho.controller;
import br.grupointegrado.trabalho.dto.TurmaRequestDTO;
import br.grupointegrado.trabalho.model.Curso;
import br.grupointegrado.trabalho.model.Turma;
import br.grupointegrado.trabalho.repository.CursoRepository;
import br.grupointegrado.trabalho.repository.TurmaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/turmas")
public class TurmaController {

    @Autowired
    private TurmaRepository turmaRepository;

    @Autowired
    private CursoRepository cursoRepository;

    @PostMapping()
    public ResponseEntity<Turma> save(@RequestBody TurmaRequestDTO dto) {
        Turma turma = new Turma();
        turma.setAno(dto.ano());
        turma.setSemestre(dto.semestre());

        Curso curso = cursoRepository.findById(dto.cursoId()).orElseThrow(() -> new IllegalArgumentException("Curso não encontrado"));
        turma.setCurso(curso);

        return ResponseEntity.ok(turmaRepository.save(turma));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Turma> findById(@PathVariable() Integer id) {
        Turma turma = this.turmaRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Turma não encontrada"));
        return ResponseEntity.ok(turma);
    }

    @GetMapping()
    public ResponseEntity<List<Turma>> findAll() {
        List<Turma> turmas = this.turmaRepository.findAll();
        return ResponseEntity.ok(turmas);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Turma> update(@RequestBody TurmaRequestDTO dto, @PathVariable Integer id) {
        Turma turma = this.turmaRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Turma não encontrada"));

        turma.setAno(dto.ano());
        turma.setSemestre(dto.semestre());

        Curso curso = cursoRepository.findById(dto.cursoId()).orElseThrow(() -> new IllegalArgumentException("Curso não encontrado"));
        turma.setCurso(curso);

        return ResponseEntity.ok(turmaRepository.save(turma));
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Integer id) {
        Turma turma = this.turmaRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Turma não encontrada"));

        this.turmaRepository.delete(turma);
    }

}
