package br.com.pi.serviceimple;

import br.com.pi.entidade.Encaminhamento;
import br.com.pi.service.IEncaminhamentoService;
import java.util.Date;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

@Stateless
public class EncaminhamentoService implements IEncaminhamentoService
{
    @PersistenceContext
    EntityManager em;
    
    @Override
    public String salvar(Encaminhamento entity)
    {
        try
        {
            if(entity.getIdEncaminhamento()!= null){
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
    public String excluir(Encaminhamento Idobj)
    {
        try
        {
            Encaminhamento en = em.find(Encaminhamento.class, Idobj.getIdEncaminhamento());
            em.remove(en);
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
            return ex.getMessage();
        }
        return null;
    }

    @Override
    public Encaminhamento obter(Integer IdObj)
    {
        return em.find(Encaminhamento.class, IdObj);
    }

    @Override
    public List<Encaminhamento> listar()
    {
        TypedQuery<Encaminhamento> typedQuery = em.createQuery("select e from Encaminhamento e ORDER BY e.dataEncaminhamento", Encaminhamento.class);
        return typedQuery.getResultList();
    }
    
    @Override
    public List<Encaminhamento> listarRel(Date dataInicio, Date dataFim)
    {
        TypedQuery<Encaminhamento> encQuery = em.createQuery("select e from Encaminhamento e where e.dataEncaminhamento between :dataInicio and :dataFim", Encaminhamento.class);
        encQuery.setParameter("dataInicio", dataInicio);
        encQuery.setParameter("dataFim", dataFim);
        return encQuery.getResultList();
    }
}
