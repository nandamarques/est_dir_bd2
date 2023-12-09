package hbd;

import java.time.LocalDate;
import java.util.*;

import hbd.entities.Aluno;
import hbd.entities.Disciplina;
import hbd.entities.Matricula;
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

        criarAluno();
        criarDisciplina();
        criarMatricula(1L, 1L);
//
//        try {
//            List<Item> itensARemover = new ArrayList<>();
//            sessionFactory.inTransaction(session -> {
//                System.out.println("Procurando Segundo Evento:");
//                itensARemover.addAll(session
//                        .createNativeQuery("SELECT * FROM tbl_item WHERE it_titulo = :titulo_procurar", Item.class)
//                        .setParameter("titulo_procurar", "Segundo Evento").getResultList());
//                itensARemover.forEach(Item::print);
//            });
//            sessionFactory.inTransaction(session -> {
//                System.out.println("Excluindo Segundo Evento:");
//                Item itemARemover = session.get(Item.class, itensARemover.get(0).getId());
//                session.delete(itemARemover);
//            });
//            sessionFactory.inTransaction(session -> {
//                long total = (long) session.createQuery("select count(*) from Item", Long.class).uniqueResult();
//                System.out.println("Total de Itens: " + total);
//            });
//        } catch (Exception e) {
//            StandardServiceRegistryBuilder.destroy(registry);
//            e.printStackTrace();
//        }
//        System.out.println("Fim!");
    }

    public static void criarAluno(){
        sessionFactory.inTransaction(session -> {
            session.persist(new Aluno("Ana Silva", LocalDate.of(1998, 03, 15), "São Paulo"));
        });

    }

    public static void criarDisciplina() {
        sessionFactory.inTransaction(session ->{
            session.persist(new Disciplina("Matemática"));
        });
    }

    public static void criarMatricula(Long alunoId, Long disciplinaId){
        sessionFactory.inTransaction(session ->{
            Aluno aluno = session.find(Aluno.class, alunoId);
            Disciplina disciplina = session.find(Disciplina.class, disciplinaId);
            session.persist(new Matricula(aluno, LocalDate.of(2022, 03, 01), disciplina));
        });
            }

    public void listarMatricula(){
        sessionFactory.inTransaction(session -> {
            List<Matricula> matricula = session.createQuery("from Matricula", Matricula.class).getResultList();
            System.out.println("Matrículas:");
            for (Matricula mat: matricula) {
                matricula.toString();
            }
//            matricula.forEach(matricula::toString);
        });
    }

}
