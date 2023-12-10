package hbd.entities;

import java.time.LocalDate;

import jakarta.persistence.*;

@Entity
@Table(name = "matriculas")
public class Matricula {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_matricula")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "id_aluno")
    private Aluno aluno;

    @Column(name = "data_matricula")
    private LocalDate dataMatricula;
    @ManyToOne
    @JoinColumn(name = "id_disciplina")
    private Disciplina disciplina;

    public Matricula() {

    }


    public Matricula(Aluno aluno, LocalDate dataMatricula, Disciplina disciplina) {
        this.aluno = aluno;
        this.dataMatricula = dataMatricula;
        this.disciplina = disciplina;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Aluno getAluno() {
        return aluno;
    }

    public void setAluno(Aluno aluno) {
        this.aluno = aluno;
    }

    public Disciplina getDisciplina() {
        return disciplina;
    }

    public void setDisciplina(Disciplina disciplina) {
        this.disciplina = disciplina;
    }

    public LocalDate getDataMatricula() {
        return dataMatricula;
    }

    public void setDataMatricula(LocalDate dataMatricula) {
        this.dataMatricula = dataMatricula;
    }

    public String toString() {
        return "Aluno: " + this.getAluno().getId() + ", " +
                "Disciplina: " + this.getDisciplina().getId() + ", " +
                "Data de matrícula: " + this.getDataMatricula();
    }

}
