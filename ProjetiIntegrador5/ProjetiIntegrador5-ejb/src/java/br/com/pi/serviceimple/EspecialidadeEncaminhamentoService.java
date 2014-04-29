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
    public String salvar(EspecialidadeEncaminhamento entity) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String excluir(EspecialidadeEncaminhamento Idobj) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public EspecialidadeEncaminhamento obter(Integer IdObj) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<EspecialidadeEncaminhamento> listar()
    {
        TypedQuery<EspecialidadeEncaminhamento> espQuery = em.createQuery("select esp from EspecialidadeEncaminhamento esp", EspecialidadeEncaminhamento.class);
        return espQuery.getResultList();
    }
    
    
}
