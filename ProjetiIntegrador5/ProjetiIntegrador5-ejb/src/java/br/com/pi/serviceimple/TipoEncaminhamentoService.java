package br.com.pi.serviceimple;

import br.com.pi.entidade.Tipoencaminhamento;
import br.com.pi.model.GraficoTipoEncaminhamentoModel;
import br.com.pi.service.ITipoEncaminhamentoService;
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
public class TipoEncaminhamentoService implements ITipoEncaminhamentoService
{
    @PersistenceContext
    EntityManager em;

    @Override
    public String salvar(Tipoencaminhamento entity) {
        try
        {
            em.merge(entity);
        }catch(Exception e)
        {
            e.printStackTrace();
            return e.getMessage();
        }
        return null;
    }

    @Override
    public String excluir(Tipoencaminhamento Idobj) {
        try
        {
            Tipoencaminhamento tp = obter(Idobj.getIdtipoEncaminhamento());
            em.remove(tp.getIdtipoEncaminhamento());
        }catch(Exception ex)
        {
            ex.printStackTrace();;
            return ex.getMessage();
        }
        return null;
    }

    @Override
    public Tipoencaminhamento obter(Short IdObj) {
        return em.find(Tipoencaminhamento.class, IdObj);
    }

    @Override
    public List<Tipoencaminhamento> listar()
    {
        TypedQuery<Tipoencaminhamento> tpQuery = em.createQuery("select tp from Tipoencaminhamento tp", Tipoencaminhamento.class);
        return tpQuery.getResultList();
    }
    
    @Override
    public List<GraficoTipoEncaminhamentoModel> consultaGrafico(){
        TypedQuery<GraficoTipoEncaminhamentoModel> query = em
                .createQuery("SELECT new br.com.pi.model.GraficoTipoEncaminhamentoModel(c.descricao, SIZE(c.encaminhamentoCollection)) FROM Tipoencaminhamento as c",GraficoTipoEncaminhamentoModel.class);
        return query.getResultList();
    }
    
    
}
