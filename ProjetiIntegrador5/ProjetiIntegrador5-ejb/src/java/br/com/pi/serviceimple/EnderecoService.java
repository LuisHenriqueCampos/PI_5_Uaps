package br.com.pi.serviceimple;

import br.com.pi.entidade.Endereco;
import br.com.pi.service.IEnderecoService;
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
public class EnderecoService implements IEnderecoService
{

    @PersistenceContext
    EntityManager em;
    
    @Override
    public String salvar(Endereco entity) {
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
    public String excluir(Endereco Idobj) {
        
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
    public Endereco obter(Integer IdObj)
    {
        return em.find(Endereco.class, IdObj);
    }

    @Override
    public List<Endereco> listar() {
        TypedQuery<Endereco> enderecoQuery = em.createQuery("select e from Endereco e", Endereco.class);
        return enderecoQuery.getResultList(); 
    }
    
    
}
