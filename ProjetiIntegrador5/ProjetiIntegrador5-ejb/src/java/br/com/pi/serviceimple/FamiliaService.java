package br.com.pi.serviceimple;

import br.com.pi.entidade.Familia;
import br.com.pi.entidade.Paciente;
import br.com.pi.service.IFamiliaService;
import java.util.ArrayList;
import java.util.Iterator;
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
public class FamiliaService implements IFamiliaService
{
    @PersistenceContext
    EntityManager em;
    
    @Override
    public String salvar(Familia entity)
    {
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
    public String excluir(Familia Idobj)
    {
        try
        {
            TypedQuery<Paciente> queryPaciente = em.createQuery("select p from Paciente p where p.idFamilia.idFamilia = :idFamilia", Paciente.class);
            queryPaciente.setParameter("idFamilia", Idobj.getIdFamilia());
            String erros = "Existe Paciente nesta família:\n";
            List<Paciente> pacientes = queryPaciente.getResultList();
            System.out.println("numeros de pacientes com familia: " + Idobj.getIdFamilia() + " é: " + pacientes.size());
            if(pacientes.size() > 0)
            {
               for(Iterator<Paciente> iterator = pacientes.iterator(); iterator.hasNext(); ) 
                {
                    erros += "-" + iterator.next().getPessoa().getNome() + "\n";
                }
                return erros;
            }
            
        }catch(Exception e)
        {
            return e.getMessage();
        }
        try
        {
            Familia f = em.find(Familia.class, Idobj.getIdFamilia());
            em.remove(f);
            return null;
        }catch(Exception ex)
        {
            ex.printStackTrace();
            return ex.getMessage();
        }
    }

    @Override
    public Familia obter(Integer IdObj)
    {
        return em.find(Familia.class, IdObj);
    }

    @Override
    public List<Familia> listar() {
        TypedQuery<Familia> familiaQuery = em.createQuery("select f from Familia f", Familia.class);
        return familiaQuery.getResultList();
    }

    @Override
    public List<Familia> listarFamiliaParaAutoComplete(String familia)
    {
        try
        {
            TypedQuery<Familia> familiaQuery = em.createQuery("select f from Familia f where f.idFamilia = :idFamilia", Familia.class);
            familiaQuery.setParameter("idFamilia", Integer.parseInt(familia));
            return familiaQuery.getResultList();
        }catch(NumberFormatException ex)
        {
            ex.printStackTrace();
            return null;
        }
    }
    
}
