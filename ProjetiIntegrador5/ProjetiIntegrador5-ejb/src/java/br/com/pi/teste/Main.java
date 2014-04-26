package br.com.pi.teste;

import br.com.pi.entidade.Bairro;
import javax.persistence.EntityManager;
import javax.persistence.Persistence;

/**
 *
 * @author petrovick
 */
public class Main
{
    static EntityManager em = Persistence.createEntityManagerFactory("ProjetiIntegrador5-ejbPU").createEntityManager();
    public static void main(String[] args)
    {
        System.out.println("++++++++++++____________________+++++++++++++++++++++++++_______________________++++++++++");
        em.getTransaction().begin();
        Bairro b = new Bairro();
        b.setBairro("Val Paraiso");
        em.persist(b);
        em.getTransaction().commit();
        
    }
}
