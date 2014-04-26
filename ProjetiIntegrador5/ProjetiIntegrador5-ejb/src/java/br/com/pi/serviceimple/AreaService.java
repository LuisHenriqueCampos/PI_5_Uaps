package br.com.pi.serviceimple;

import br.com.pi.entidade.Area;
import br.com.pi.service.IAreaService;
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
public class AreaService implements IAreaService
{
    @PersistenceContext
    EntityManager em;

    @Override
    public String salvar(Area entity) {
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
    public String excluir(Area Idobj) {
        
        try
        {
            em.remove(Idobj);
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
            return ex.getMessage();
        }
        return null;
    }
    
    @Override
    public List<Area> listar() {
        TypedQuery<Area> areaQuery = em.createQuery("select a from Area a", Area.class);
        return areaQuery.getResultList();
    }

    @Override
    public Area obter(Integer IdObj) {
        return em.find(Area.class, IdObj);
    }
    
    
}
