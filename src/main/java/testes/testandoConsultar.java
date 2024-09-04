package testes;

import model.Pessoa;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class testandoConsultar {
    public static void main(String[] args){
        EntityManagerFactory fabricEM = Persistence.createEntityManagerFactory("SistemaPU");
        EntityManager em = fabricEM.createEntityManager();
        Pessoa pessoa =  em.find(Pessoa.class, 2L);

        System.out.println(pessoa.getNome() + " " + pessoa.getIdade());
        em.close();
        fabricEM.close();
    }
}
