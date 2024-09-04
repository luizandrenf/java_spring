package testes;

import dao.PessoaDAO;
import model.Pessoa;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class testaPessoaDao {
    public static void main (String[] args){
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("SistemaPU");
        EntityManager em = emf.createEntityManager();

        PessoaDAO pessoaDAO = new PessoaDAO(em);


        Pessoa p = new Pessoa("jsbjs", "Luiz", 19);
        //pessoaDAO.inserir(pessoa);
        pessoaDAO.inserir(p);

        em.close();
        emf.close();
    }
}
