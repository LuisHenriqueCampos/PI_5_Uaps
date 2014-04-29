package br.com.pi.serviceimple;

import br.com.pi.entidade.Sexo;
import br.com.pi.service.ISexoService;
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
public class SexoService implements ISexoService
{
    @PersistenceContext
    EntityManager em;
    
    @Override
    public String salvar(Sexo entity)
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
    public String excluir(Sexo Idobj)
    {
        try
        {
            Sexo s = em.find(Sexo.class, Idobj.getIdSexo());
            em.remove(s);
            return null;
        }catch(Exception ex)
        {
            ex.printStackTrace();
            return ex.getMessage();
        }
    }

    @Override
    public Sexo obter(Integer IdObj) {
        return em.find(Sexo.class, IdObj);
    }

    @Override
    public List<Sexo> listar()
    {
        TypedQuery<Sexo> querySexo = em.createQuery("select s from Sexo s", Sexo.class);
        return querySexo.getResultList();
        
    }
    
    
}
