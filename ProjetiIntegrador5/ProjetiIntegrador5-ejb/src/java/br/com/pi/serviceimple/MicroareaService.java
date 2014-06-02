package br.com.pi.serviceimple;

import br.com.pi.entidade.Area;
import br.com.pi.entidade.Microarea;
import br.com.pi.service.IMicroareaService;
import java.util.List;
import javax.ejb.Stateful;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

/**
 *
 * @author petrovick
 */
@Stateful
public class MicroareaService  implements IMicroareaService
{

    @PersistenceContext
    EntityManager em;

    @Override
    public String salvar(Microarea entity) {
        try
        {
            System.out.println("Entrou no salvar");
            em.merge(entity);
            System.out.println("Salvou");
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
            return ex.getMessage();
        }
        return null;
    }

    @Override
    public String excluir(Microarea Idobj) {
        
        try
        {
            Microarea m = obter(Idobj.getIdMicroArea());
            em.remove(m);
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
            return ex.getMessage();
        }
        return null;
    }

    @Override
    public Microarea obter(Integer IdObj)
    {
        return em.find(Microarea.class, IdObj);
    }

    @Override
    public List<Microarea> listar() {
        TypedQuery<Microarea> bairroQuery = em.createQuery("select a from Microarea a", Microarea.class);
        return bairroQuery.getResultList();
    }

    @Override
    public List<Microarea> listarPorArea(Area area) {
        TypedQuery<Microarea> microQuery
                = em.createQuery("select a from Microarea a where a.idArea.idArea = :id", Microarea.class);
        microQuery.setParameter("id", area.getIdArea());
        return microQuery.getResultList();
    }
}
