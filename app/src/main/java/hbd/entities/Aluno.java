package hbd.entities;

import java.time.LocalDate;
import java.util.List;

import jakarta.persistence.*;

@Entity
@Table(name = "alunos")
public class Aluno {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_aluno")
    private Long id;
    @Column(name = "nome_aluno")
    private String nome;
    @Column(name = "data_nascimento")
    private LocalDate dataNascimento;

    @Column(name = "cidade_natal")
    private String cidadeNatal;

    @OneToMany(mappedBy = "aluno")
    private List<Matricula> matriculas;
    public Aluno(){
    }

    public Aluno(String nome, LocalDate dataNascimento, String cidadeNatal) {
        this.nome = nome;
        this.dataNascimento = dataNascimento;
        this.cidadeNatal = cidadeNatal;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public LocalDate getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(LocalDate dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public String getCidadeNatal() {
        return cidadeNatal;
    }

    public void setCidadeNatal(String cidadeNatal) {
        this.cidadeNatal = cidadeNatal;
    }

    public String toString() {
        return "Aluno: " + this.getId() + ", " + "Nome: " + this.getNome() + ", " + "Data de Nascimento: " + this.getDataNascimento() + ", " + "Cidade Natal: " + this.getCidadeNatal();
    }
}


