package br.com.pi.serviceimple;

import br.com.pi.entidade.Area;
import br.com.pi.entidade.Postosaude;
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
            if(entity.getIdArea()!=null){
                em.merge(entity);
            }else{
                em.persist(entity);
            }
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
            Area a = em.find(Area.class, Idobj.getIdArea());
           if(a.getMicroareaCollection().isEmpty()){
                em.remove(a);
            }else{
                return "Existe Dependências deste Registro. O Registro não poderá ser Excluído.";
            }
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

    @Override
    public List<Area> listarPorPosto(Postosaude postosaude) {
        TypedQuery<Area> areaQuery
                = em.createQuery("select a from Area a where a.idPostoSaude.idPostoSaude = :id", Area.class);
        areaQuery.setParameter("id", postosaude.getIdPostoSaude());
        return areaQuery.getResultList();
    }
    
    
}
