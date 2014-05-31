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
    public String excluir(Paciente Idobj)
    {
        try
        {
            Paciente p = em.find(Paciente.class, Idobj.getIdPessoaPaciente());
            em.remove(p);
            return null;
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
            return ex.getMessage();
        }
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
