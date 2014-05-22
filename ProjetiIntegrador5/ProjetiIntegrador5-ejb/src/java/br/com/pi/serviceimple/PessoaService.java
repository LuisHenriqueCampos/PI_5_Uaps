/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package br.com.pi.serviceimple;

import br.com.pi.service.IPessoaService;
import br.com.pi.entidade.Pessoaa;
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
public class PessoaService implements IPessoaService
{
    @PersistenceContext
    EntityManager em;

    @Override
    public String salvar(Pessoaa entity) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String excluir(Pessoaa Idobj) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Pessoaa obter(Integer IdObj) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Pessoaa> listar()
    {
        TypedQuery pesQuery = em.createQuery("select p from Pessoa p", Pessoaa.class);
        return pesQuery.getResultList();
    }
}
