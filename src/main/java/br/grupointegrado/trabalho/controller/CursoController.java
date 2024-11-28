package br.grupointegrado.trabalho.controller;
import br.grupointegrado.trabalho.dto.CursoRequestDTO;
import br.grupointegrado.trabalho.model.Curso;
import br.grupointegrado.trabalho.repository.CursoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/cursos")
public class CursoController {

    @Autowired
    private CursoRepository cursoRepository;

    @PostMapping()
    public ResponseEntity<Curso> save(@RequestBody CursoRequestDTO dto) {
        Curso curso = new Curso();
        curso.setNome(dto.nome());
        curso.setCodigo(dto.codigo());
        curso.setCargaHoraria(dto.cargaHoraria());

        return ResponseEntity.ok(cursoRepository.save(curso));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Curso> findById(@PathVariable() Integer id) {
        Curso curso = this.cursoRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Curso não encontrado"));

        return ResponseEntity.ok(curso);
    }

    @GetMapping()
    public ResponseEntity<List<Curso>> findAll() {
        List<Curso> cursos = this.cursoRepository.findAll();
        return ResponseEntity.ok(cursos);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Curso> update(@RequestBody CursoRequestDTO dto, @PathVariable Integer id) {
        Curso curso = this.cursoRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Curso não encontrado"));

        curso.setNome(dto.nome());
        curso.setCodigo(dto.codigo());
        curso.setCargaHoraria(dto.cargaHoraria());

        return ResponseEntity.ok(cursoRepository.save(curso));
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Integer id) {
        Curso curso = this.cursoRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Curso não encontrado"));

        this.cursoRepository.delete(curso);
    }

}
