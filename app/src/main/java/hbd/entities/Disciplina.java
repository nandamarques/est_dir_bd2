package hbd.entities;

import jakarta.persistence.*;

import java.util.List;


@Entity
@Table(name = "disciplinas")
public class Disciplina {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_disciplina")
    private Long id;

    @Column(name = "nome_disciplina")
    private String nomeDisciplina;


    @OneToMany(mappedBy = "disciplina")
    private List<Matricula> matriculas;
    public Disciplina() {

    }
    public Disciplina(String nomeDisciplina) {
        this.nomeDisciplina = nomeDisciplina;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNomeDisciplina() {
        return nomeDisciplina;
    }

    public void setNomeDisciplina(String nomeDisciplina) {
        this.nomeDisciplina = nomeDisciplina;
    }

    public String toString(){
        return "Disciplina: " + this.getId() + ", " + "Nome da Disciplina: " + this.getNomeDisciplina();
    }
}
