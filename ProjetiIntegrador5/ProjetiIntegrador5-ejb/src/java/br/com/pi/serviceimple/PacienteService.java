package br.com.pi.serviceimple;

import br.com.pi.entidade.Paciente;
import br.com.pi.service.IPacienteService;
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
public class PacienteService implements IPacienteService
{
    @PersistenceContext
    EntityManager em;

    @Override
    public String salvar(Paciente entity) {
        if(entity.getPessoa().getIdPessoa() == null)
        {
            TypedQuery<Paciente> pacienteQuery = em.createQuery("Select p from Paciente p WHERE p.cns = :param",Paciente.class);
            pacienteQuery.setParameter("param", entity.getCns());
            
            if(pacienteQuery.getResultList().size() > 0)
            {
                return "Não é possível cadastrar dois pacientes com um mesmo cns";
            }
            
            else
            {
                em.merge(entity);
            }
        }
        
        else
        {
            try
            {
                em.merge(entity);
            }
            catch(Exception ex)
            {
                ex.printStackTrace();
                return ex.getMessage();
            }
        }
        
        return null;
    }

    @Override
    public String excluir(Paciente Idobj)
    {
        try
        {
            Paciente p = em.find(Paciente.class, Idobj.getPessoa().getIdPessoa());
            if(p.getEncaminhamentoCollection().isEmpty()){
                em.remove(p);
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
    public Paciente obter(Integer IdObj)
    {
        return em.find(Paciente.class, IdObj);
    }

    @Override
    public List<Paciente> listar()
    {
        TypedQuery pacQuery = em.createQuery("select p from Paciente p order by p.pessoa.nome", Paciente.class);
        return pacQuery.getResultList();
    }

    @Override
    public List<Paciente> listarRel(String pesq)
    {
        TypedQuery<Paciente> pacQuery = em.createQuery("select p from Paciente p where p.pessoa.nome like :nome", Paciente.class);
        pacQuery.setParameter("nome", "%" + pesq + "%");
        return pacQuery.getResultList();
    }
    
    
}
