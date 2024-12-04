package br.grupointegrado.trabalho.controller;
import br.grupointegrado.trabalho.dto.DisciplinaRequestDTO;
import br.grupointegrado.trabalho.model.Curso;
import br.grupointegrado.trabalho.model.Disciplina;
import br.grupointegrado.trabalho.model.Nota;
import br.grupointegrado.trabalho.model.Professor;
import br.grupointegrado.trabalho.repository.CursoRepository;
import br.grupointegrado.trabalho.repository.DisciplinaRepository;
import br.grupointegrado.trabalho.repository.NotaRepository;
import br.grupointegrado.trabalho.repository.ProfessorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/disciplinas")
public class DisciplinaController {

    @Autowired
    private DisciplinaRepository disciplinaRepository;

    @Autowired
    private ProfessorRepository professorRepository;

    @Autowired
    private CursoRepository cursoRepository;

    @Autowired
    private NotaRepository notaRepository;

    @PostMapping()
    public ResponseEntity<Disciplina> save(@RequestBody DisciplinaRequestDTO dto) {
        Disciplina disciplina = new Disciplina();
        disciplina.setNome(dto.nome());
        disciplina.setCodigo(dto.codigo());

        Curso curso = cursoRepository.findById(dto.cursoId()).orElseThrow(() -> new IllegalArgumentException("Curso não encontrado"));
        disciplina.setCurso(curso);

        Professor professor = professorRepository.findById(dto.professorId()).orElseThrow(() -> new IllegalArgumentException("Professor não encontrado"));
        disciplina.setProfessor(professor);

        return ResponseEntity.ok(disciplinaRepository.save(disciplina));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Disciplina> findById(@PathVariable() Integer id) {
        Disciplina disciplina = this.disciplinaRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Disciplina não encontrada"));
        return ResponseEntity.ok(disciplina);
    }

    @GetMapping()
    public ResponseEntity<List<Disciplina>> findAll() {
        List<Disciplina> disciplinas = this.disciplinaRepository.findAll();
        return ResponseEntity.ok(disciplinas);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Disciplina> update(@RequestBody DisciplinaRequestDTO dto, @PathVariable Integer id) {
        Disciplina disciplina = this.disciplinaRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Disciplina não encontrada"));

        disciplina.setNome(dto.nome());
        disciplina.setCodigo(dto.codigo());

        Curso curso = cursoRepository.findById(dto.cursoId()).orElseThrow(() -> new IllegalArgumentException("Curso não encontrado"));
        disciplina.setCurso(curso);

        Professor professor = professorRepository.findById(dto.professorId()).orElseThrow(() -> new IllegalArgumentException("Professor não encontrado"));
        disciplina.setProfessor(professor);

        return ResponseEntity.ok(disciplinaRepository.save(disciplina));
    }

    @GetMapping("/{id}/notas")
    public ResponseEntity<List<Nota>> findNotas(@PathVariable Integer id) {
        Disciplina disciplina = this.disciplinaRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Disciplina não encontrada."));

        List<Nota> notas = this.notaRepository.findByDisciplina(disciplina);

        return ResponseEntity.ok(notas);
    }


    @DeleteMapping("/{id}")
    public void delete(@PathVariable Integer id) {
        Disciplina disciplina = this.disciplinaRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Disciplina não encontrada"));

        this.disciplinaRepository.delete(disciplina);
    }

}
