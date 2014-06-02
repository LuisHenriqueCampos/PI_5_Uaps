package br.com.pi.serviceimple;

import br.com.pi.entidade.Endereco;
import br.com.pi.service.IEnderecoService;
import java.util.List;
import javax.ejb.Stateful;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

/**
 *
 * @author petrovick
 */
@Stateful
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
            Endereco end = em.find(Endereco.class, Idobj.getIdEndereco());
            if(end.getFamiliaCollection().isEmpty()){
                em.remove(end);
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
    public Endereco obter(Integer IdObj)
    {
        return em.find(Endereco.class, IdObj);
    }

    @Override
    public List<Endereco> listar() {
        TypedQuery<Endereco> enderecoQuery = em.createQuery("select e from Endereco e", Endereco.class);
        return enderecoQuery.getResultList(); 
    }
    
    @Override
    public List<Endereco> listarRel(String pesq)
    {
        TypedQuery<Endereco> endQuery = em.createQuery("select e from Endereco e where e.rua like :rua", Endereco.class);
        endQuery.setParameter("rua", "%" + pesq + "%");
        return endQuery.getResultList();
    }
}
