package testes;

import model.Cidade;
import model.Estado;
import model.Pessoa;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class TestePessoaComCidade {
    public static void inserir(){
        EntityManagerFactory fabricaEM = Persistence.createEntityManagerFactory("SistemaPU");

        EntityManager em = fabricaEM.createEntityManager();

        Pessoa pessoa = new Pessoa("12345678905","Pessoa com Cidade",31);

        Estado estado = new Estado();
        estado.setNome("Amazonas");
        estado.setSigla("AM");

        Cidade cidade = new Cidade();
        cidade.setNome("Manaus");
        cidade.setEstado(estado);

        pessoa.setCidade(cidade);

        EntityTransaction transacao = em.getTransaction();

        try {
            transacao.begin();

            em.persist(estado);

            em.persist(cidade);

            em.persist(pessoa);

            transacao.commit();

        }catch(Exception e) {

            if(transacao.isActive()) {
                transacao.rollback();
            }
            throw new RuntimeException(e);

        }finally{
            em.close();
            fabricaEM.close();
        }
    }

    public static void inserirComCidadeExistenteNoBD(){

        EntityManagerFactory fabricaEM = Persistence.createEntityManagerFactory("SistemaPU");

        EntityManager em = fabricaEM.createEntityManager();

        Pessoa pessoa = new Pessoa("12345678915","Pessoa 02 com Cidade 01",31);

        EntityTransaction transacao = em.getTransaction();

        try {
            transacao.begin();

            Cidade cidade = em.find(Cidade.class,1L);

            pessoa.setCidade(cidade);

            em.persist(pessoa);

            transacao.commit();

        }catch(Exception e) {

            if(transacao.isActive()) {
                transacao.rollback();
            }
            throw new RuntimeException(e);

        }finally{
            em.close();
            fabricaEM.close();
        }
    }

    public static void consultar(){

        EntityManagerFactory fabricaEM = Persistence.createEntityManagerFactory("SistemaPU");

        EntityManager em = fabricaEM.createEntityManager();

        try {

            Pessoa pessoa = em.find(Pessoa.class,1L);

            System.out.println(pessoa);

        }catch(Exception e) {

            throw new RuntimeException(e);

        }finally{
            em.close();
            fabricaEM.close();
        }
    }


    public static void main(String[] args) {
        //inserir();
        consultar();
       // inserirComCidadeExistenteNoBD();

    }

}