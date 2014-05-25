package br.com.pi.serviceimple;

import br.com.pi.entidade.EspecialidadeEncaminhamento;
import br.com.pi.service.IEspecialidadeEncaminhamentoService;
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
public class EspecialidadeEncaminhamentoService implements IEspecialidadeEncaminhamentoService
{
    @PersistenceContext
    EntityManager em;

    @Override
    public String salvar(EspecialidadeEncaminhamento entity) {
        try{
            if(entity.getIdEspecialidade() != null){
                em.merge(entity);
            }
            else{
                em.persist(entity);
            }
        }catch(Exception ex){
            ex.printStackTrace();
            return ex.getMessage();
        }
        return null;
    }

    @Override
    public String excluir(EspecialidadeEncaminhamento Idobj) {
        
        try
        {
            EspecialidadeEncaminhamento e = obter(Idobj.getIdEspecialidade());
            em.remove(e);
            return null;
        }catch(Exception ex)
        {
            ex.printStackTrace();
            return ex.getMessage();
        }
    }

    @Override
    public EspecialidadeEncaminhamento obter(Integer IdObj) {
        return em.find(EspecialidadeEncaminhamento.class, IdObj);
    }

    @Override
    public List<EspecialidadeEncaminhamento> listar()
    {
        TypedQuery<EspecialidadeEncaminhamento> espQuery = em.createQuery("select esp from EspecialidadeEncaminhamento esp", EspecialidadeEncaminhamento.class);
        return espQuery.getResultList();
    }
    
    
}
