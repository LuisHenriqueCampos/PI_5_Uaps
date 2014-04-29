package br.com.pi.serviceimple;

import br.com.pi.entidade.Encaminhamento;
import br.com.pi.service.IEncaminhamentoService;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

/**
 *
 * @author petrovick
 */
@Stateless
public class EncaminhamentoService implements IEncaminhamentoService
{
    @PersistenceContext
    EntityManager em;
    @Override
    public String salvar(Encaminhamento entity)
    {
        try
        {
            
            em.merge(entity);
            System.out.println("Salvou");
            return null;
        }
        catch(Exception ex)
        {
            System.out.println("Deu erro ao salvar");
            ex.printStackTrace();
            return ex.getMessage();
        }
    }

    @Override
    public String excluir(Encaminhamento Idobj)
    {
        try
        {
            Encaminhamento en = em.find(Encaminhamento.class, Idobj.getIdEncaminhamento());
            em.remove(en);
            return null;
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
            return ex.getMessage();
        }
    }

    @Override
    public Encaminhamento obter(Integer IdObj)
    {
        return em.find(Encaminhamento.class, IdObj);
    }

    @Override
    public List<Encaminhamento> listar()
    {
        TypedQuery<Encaminhamento> typedQuery = em.createQuery("select e from Encaminhamento e", Encaminhamento.class);
        return typedQuery.getResultList();
    }
    
}
