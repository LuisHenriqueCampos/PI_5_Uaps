/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package br.com.pi.serviceimple;

import br.com.pi.entidade.Medicoenfermeira;
import br.com.pi.service.IMedicoEnfermeiraService;
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
public class MedicoEnfermeiraService implements IMedicoEnfermeiraService
{
    @PersistenceContext
    EntityManager em;

    @Override
    public String salvar(Medicoenfermeira entity) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String excluir(Medicoenfermeira Idobj) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Medicoenfermeira obter(Integer IdObj) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Medicoenfermeira> listar()
    {
        TypedQuery<Medicoenfermeira> medicoenfermeiraQuery = em.createQuery("select m from Medicoenfermeira m", Medicoenfermeira.class);
        return medicoenfermeiraQuery.getResultList();
        
    }
    
    
}
