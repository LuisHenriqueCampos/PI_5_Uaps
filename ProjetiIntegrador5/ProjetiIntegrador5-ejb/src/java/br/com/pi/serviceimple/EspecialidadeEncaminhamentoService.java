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
    public String salvar(EspecialidadeEncaminhamento especialidadeEncaminhamento) {
        try{
            if(especialidadeEncaminhamento.getIdEspecialidade() != null){
                em.merge(especialidadeEncaminhamento);
            }
            else{
                em.persist(especialidadeEncaminhamento);
            }
        }catch(Exception ex){
            ex.printStackTrace();
            return ex.getMessage();
        }
        return null;
    }

    @Override
    public String excluir(Integer idEspecialidade) {
        
        try
        {
            EspecialidadeEncaminhamento e = obter(idEspecialidade);
            em.remove(e);
            return null;
        }catch(Exception ex)
        {
            ex.printStackTrace();
            return ex.getMessage();
        }
    }

    @Override
    public EspecialidadeEncaminhamento obter(Integer id) {
        EspecialidadeEncaminhamento especialidadeEncaminhamento = 
                em.find(EspecialidadeEncaminhamento.class, id);
        return especialidadeEncaminhamento;
    }

    @Override
    public List<EspecialidadeEncaminhamento> listar()
    {
        TypedQuery<EspecialidadeEncaminhamento> espQuery = 
                em.createQuery("SELECT esp FROM EspecialidadeEncaminhamento esp", EspecialidadeEncaminhamento.class);
        return espQuery.getResultList();
    }
    
    
}
