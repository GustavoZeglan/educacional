package br.grupointegrado.trabalho.controller;

import br.grupointegrado.trabalho.dto.AlunoRequestDTO;
import br.grupointegrado.trabalho.dto.DisciplinaResponseDTO;
import br.grupointegrado.trabalho.dto.NotaResponseDTO;
import br.grupointegrado.trabalho.dto.RelatorioResponseDTO;
import br.grupointegrado.trabalho.model.Aluno;
import br.grupointegrado.trabalho.model.Disciplina;
import br.grupointegrado.trabalho.model.Matricula;
import br.grupointegrado.trabalho.model.Nota;
import br.grupointegrado.trabalho.repository.AlunoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/alunos")
public class AlunoController {

    @Autowired
    private AlunoRepository repository;

    @GetMapping()
    public List<Aluno> findAll() {
        return this.repository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Aluno> findById(@PathVariable Integer id) {
        Aluno aluno = this.repository.findById(id).orElseThrow(() -> new IllegalArgumentException("Aluno não encontrado"));

        return ResponseEntity.ok(aluno);
    }

    @PostMapping()
    public ResponseEntity<Aluno> save(@RequestBody AlunoRequestDTO dto) {
    	Aluno aluno = new Aluno();
    	aluno.setNome(dto.nome());
    	aluno.setEmail(dto.email());
    	aluno.setMatricula(dto.matricula());
    	aluno.setDataNascimento(dto.dataNascimento());

        return ResponseEntity.ok(this.repository.save(aluno));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Aluno> update(@PathVariable Integer id, @RequestBody AlunoRequestDTO dto) {
    	Aluno aluno = this.repository.findById(id).orElseThrow(() -> new IllegalArgumentException("Aluno não encontrado"));

    	aluno.setNome(dto.nome());
    	aluno.setMatricula(dto.matricula());
    	aluno.setDataNascimento(dto.dataNascimento());

        return ResponseEntity.ok(this.repository.save(aluno));
    }


    @GetMapping("/{aluno_id}/boletim")
    public ResponseEntity<RelatorioResponseDTO> findNotas(@PathVariable Integer aluno_id) {
        Aluno aluno = this.repository.findById(aluno_id)
                .orElseThrow(() -> new IllegalArgumentException("Aluno não encontrado."));

        List<NotaResponseDTO> notas = new ArrayList<>();

        if (!aluno.getMatricula().isEmpty()) {
            for (Matricula matricula : aluno.getMatriculas()) {
                for (Nota nota : matricula.getNotas()) {
                    notas.add(
                            new NotaResponseDTO(
                                    nota.getNota(),
                                    nota.getDataLancamento(),
                                    new DisciplinaResponseDTO(
                                            nota.getDisciplina().getNome(),
                                            nota.getDisciplina().getCodigo()
                                    )
                            )
                    );
                }
            }
        }
        return ResponseEntity.ok(new RelatorioResponseDTO(notas));
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Integer id) {
        Aluno aluno = this.repository.findById(id).orElseThrow(() -> new IllegalArgumentException("Aluno não encontrado"));

        this.repository.delete(aluno);
    }
}