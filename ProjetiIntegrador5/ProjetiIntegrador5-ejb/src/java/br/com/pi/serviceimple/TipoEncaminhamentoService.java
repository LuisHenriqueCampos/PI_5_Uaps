package br.com.pi.serviceimple;

import br.com.pi.entidade.Tipoencaminhamento;
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
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String excluir(Tipoencaminhamento Idobj) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Tipoencaminhamento obter(Integer IdObj) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Tipoencaminhamento> listar()
    {
        TypedQuery<Tipoencaminhamento> tpQuery = em.createQuery("select tp from Tipoencaminhamento tp", Tipoencaminhamento.class);
        return tpQuery.getResultList();
    }
    
    
}
