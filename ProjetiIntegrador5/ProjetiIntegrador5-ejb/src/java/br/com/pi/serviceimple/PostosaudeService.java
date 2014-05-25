package br.com.pi.serviceimple;

import br.com.pi.service.IPostosaudeService;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import br.com.pi.entidade.Postosaude;
import java.util.List;
import javax.persistence.TypedQuery;

/**
 *
 * @author petrovick
 */
@Stateless
public class PostosaudeService implements IPostosaudeService
{
    
    @PersistenceContext
    EntityManager em;

    @Override
    public String salvar(Postosaude entity) {
        try
        {
            em.merge(entity);
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
            return ex.getMessage();
        }
        return null;
    }

    @Override
    public String excluir(Postosaude Idobj) {
        
        try
        {
            Postosaude posto = em.find(Postosaude.class, Idobj.getIdPostoSaude());
            em.remove(posto);
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
            return ex.getMessage();
        }
        return null;
    }

    @Override
    public Postosaude obter(Integer IdObj)
    {
        return em.find(Postosaude.class, IdObj);
    }

    @Override
    public List<Postosaude> listar() {
        TypedQuery<Postosaude> postosaudeQuery = em.createQuery("select a from Postosaude a", Postosaude.class);
        return postosaudeQuery.getResultList();
    }

    @Override
    public List<Postosaude> listarRel(String pesq)
    {
        System.out.println("->" + pesq);
        TypedQuery<Postosaude> posQuery = em.createQuery("select pos from Postosaude pos where pos.nomePosto like :posnome", Postosaude.class);
        posQuery.setParameter("posnome", "%" + pesq + "%");
        List<Postosaude> ppp = posQuery.getResultList();
        for(Postosaude pp : ppp)
        {
            System.out.println("::::" + pp.getNomePosto());
        }
        return ppp;
    }
    
}
