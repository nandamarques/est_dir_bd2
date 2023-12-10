package hbd;

import java.time.LocalDate;
import java.util.*;

import hbd.entities.Aluno;
import hbd.entities.Disciplina;
import hbd.entities.Matricula;
import jakarta.persistence.Query;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

public class App {

    static final StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
            .build();
    static final SessionFactory sessionFactory = new MetadataSources(registry)
            .addAnnotatedClasses(Aluno.class, Disciplina.class, Matricula.class)
            .buildMetadata()
            .buildSessionFactory();

    public static void main(String[] args) {
        System.out.println("Iniciando...");


        try {
            System.out.println("----Criação e inserts das tabelas----");
            System.out.println("--Alunos--");
            criarAluno();
            listarAlunos();

            System.out.println("--Disciplinas--");
            criarDisciplina();
            listarDisciplinas();

            System.out.println("--Matrículas--");
            criarMatricula(1L, 1L) ;
            criarMatricula(1L, 2L) ;
            criarMatricula(2L, 1L) ;
            criarMatricula(2L, 3L) ;
            listarMatriculas();

            System.out.println("----Consulta do nome e data de nascimento de todos os alunos----");
            consultarAlunos();

            System.out.println("----Consulta do total de alunos matriculados----");
            totalAlunosMatriculados();

            System.out.println("Operação de Update");
            updateNomeAluno("João Silva", "Ana Silva");
            listarAlunos();
        } catch (Exception e){
            StandardServiceRegistryBuilder.destroy(registry);
            e.printStackTrace();
        }
        System.exit(0);
    }

    public static void criarAluno(){
        sessionFactory.inTransaction(session -> {
            session.persist(new Aluno("Ana Silva", LocalDate.of(1998, 03, 15), "São Paulo"));
            session.persist(new Aluno("Pedro", LocalDate.of(1997, 06, 22), "Rio de Janeiro"));
            session.persist(new Aluno("Mariana Costa", LocalDate.of(1999, 12, 10), "Belo Horizonte"));
            session.persist(new Aluno("Fernando Mendes", LocalDate.of(2000, 05, 20), "Salvador"));

        });
    }

    public static void listarAlunos(){
        sessionFactory.inTransaction(session -> {
            List<Aluno> alunos = session.createQuery("FROM Aluno", Aluno.class).getResultList();
            System.out.println("Lista de Alunos:");
            for (Aluno al: alunos) {
                System.out.println(al.toString());
            }
        });
    }

    public static void criarDisciplina() {
        sessionFactory.inTransaction(session ->{
            session.persist(new Disciplina("Matemática"));
            session.persist(new Disciplina("Física"));
            session.persist(new Disciplina("Química"));
            session.persist(new Disciplina("Geografia"));
        });
    }

    public static void listarDisciplinas(){
        sessionFactory.inTransaction(session -> {
            List<Disciplina> disciplinas = session.createQuery("FROM Disciplina", Disciplina.class).getResultList();
            System.out.println("Lista de Disciplinas:");
            for (Disciplina dis: disciplinas) {
                System.out.println(dis.toString());
            }
        });
    }

    public static void criarMatricula(Long alunoId, Long disciplinaId){
        sessionFactory.inTransaction(session ->{
            Aluno aluno = session.find(Aluno.class, alunoId);
            Disciplina disciplina = session.find(Disciplina.class, disciplinaId);
            session.persist(new Matricula(aluno, LocalDate.of(2022, 03, 01), disciplina));
        });
            }

    public static void listarMatriculas(){
        sessionFactory.inTransaction(session -> {
            List<Matricula> matricula = session.createQuery("from Matricula", Matricula.class).getResultList();
            System.out.println("Lista de matrículas:");
            for (Matricula mat: matricula) {
                System.out.println(mat.toString());
            }
        });
    }

    public static void consultarAlunos(){
        sessionFactory.inTransaction(session -> {
            System.out.println("Nome e Data de Nascimento de todos os alunos: ");
            Query query = session.createQuery("SELECT nome, dataNascimento FROM Aluno");
            List<Object[]> resultado = query.getResultList();
            for (Object[] obj: resultado) {
                String nome = (String) obj[0];
                LocalDate dataNascimento = (LocalDate) obj[1];
                System.out.println("Nome: " + nome + ", Data de Nascimento: " + dataNascimento);
            }
        });
    }

    public static void totalAlunosMatriculados(){
        sessionFactory.inTransaction(session -> {
            long totalAlunos = (long)
                    session.createQuery("SELECT COUNT(DISTINCT al.id) FROM Aluno al JOIN al.matriculas ma",
                            Long.class).uniqueResult();
                System.out.println("Total de Alunos matriculados: " + totalAlunos);
        });
    }

    public static void updateNomeAluno(String novoNome, String nomeAntigo) {
        sessionFactory.inTransaction(session -> {
            System.out.println("Modificando o nome " + nomeAntigo + " por: " + novoNome);
                    Query query = session.createQuery("UPDATE Aluno al SET al.nome = :novoNome WHERE al.nome = :nomeAntigo");
                    query.setParameter("novoNome", novoNome);
                    query.setParameter("nomeAntigo", nomeAntigo);

                    int linhasAtualizadas = query.executeUpdate();
                });
    }






}
