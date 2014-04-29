package br.com.pi.serviceimple;

import br.com.pi.entidade.Familia;
import br.com.pi.service.IFamiliaService;
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
public class FamiliaService implements IFamiliaService
{
    @PersistenceContext
    EntityManager em;
    
    @Override
    public String salvar(Familia entity)
    {
        try
        {
            em.merge(entity);
            return null;
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
            return ex.getMessage();
        }
    }

    @Override
    public String excluir(Familia Idobj)
    {
        try
        {
            Familia f = em.find(Familia.class, Idobj.getIdFamilia());
            em.remove(f);
            return null;
        }catch(Exception ex)
        {
            return ex.getMessage();
        }
    }

    @Override
    public Familia obter(Integer IdObj)
    {
        return em.find(Familia.class, IdObj);
    }

    @Override
    public List<Familia> listar() {
        TypedQuery<Familia> familiaQuery = em.createQuery("select f from Familia f", Familia.class);
        return familiaQuery.getResultList();
    }

    @Override
    public List<Familia> listarFamiliaParaAutoComplete(String familia)
    {
        try
        {
            TypedQuery<Familia> familiaQuery = em.createQuery("select f from Familia f where f.idFamilia = :idFamilia", Familia.class);
            familiaQuery.setParameter("idFamilia", Integer.parseInt(familia));
            return familiaQuery.getResultList();
        }catch(NumberFormatException ex)
        {
            ex.printStackTrace();
            return null;
        }
    }
    
}
