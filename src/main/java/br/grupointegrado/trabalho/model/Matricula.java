package br.grupointegrado.trabalho.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "matriculas")
public class Matricula {

    @Id()
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;


    @ManyToOne
    @JoinColumn(name = "aluno_id",referencedColumnName = "id")
    @JsonIgnoreProperties("matriculas")
    private Aluno aluno;

    @ManyToOne
    @JoinColumn(name = "turma_id",referencedColumnName = "id")
    @JsonIgnoreProperties("matriculas")
    private Turma turma;


    @OneToMany(mappedBy = "matricula",cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnoreProperties("matricula")
    private List<Nota> notas;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Aluno getAluno() {
        return aluno;
    }

    public void setAluno(Aluno aluno) {
        this.aluno = aluno;
    }

    public Turma getTurma() {
        return turma;
    }

    public void setTurma(Turma turma) {
        this.turma = turma;
    }

    public List<Nota> getNotas() {
        return notas;
    }

    public void setNotas(List<Nota> notas) {
        this.notas = notas;
    }
}
