package br.com.pi.serviceimple;

import br.com.pi.entidade.Agentesaude;
import br.com.pi.service.IAgenteSaude;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

/**
 *
 * @author Luis
 */
@Stateless
public class AgenteSaudeService implements IAgenteSaude{
    
    @PersistenceContext
    EntityManager em;

    @Override
    public String salvar(Agentesaude entity) {
        try{
            if(entity.getPessoa().getIdPessoa()!=null){
                em.merge(entity);
            }else{
                em.persist(entity);
            }
        }catch(Exception ex){
            ex.printStackTrace();
            return ex.getMessage();
        }
        return null;
    }

    @Override
    public String excluir(Agentesaude Idobj) {
        try{
            Agentesaude ags = em.find(Agentesaude.class, Idobj.getPessoa().getIdPessoa());
            em.remove(ags);
        }catch(Exception ex){
            ex.printStackTrace();
            return ex.getMessage();
        }
        return null;
    }

    @Override
    public Agentesaude obter(Integer IdObj) {
        return em.find(Agentesaude.class, IdObj);
    }

    @Override
    public List<Agentesaude> listar() {
        TypedQuery<Agentesaude> agquery = em
                .createQuery("SELECT ag FROM Agentesaude ag ORDER BY ag.pessoa.nome",Agentesaude.class);
        return agquery.getResultList();
    }
    
}
