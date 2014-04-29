/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package br.com.pi.serviceimple;

import br.com.pi.entidade.Area;
import br.com.pi.service.IBairroService;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import br.com.pi.entidade.Bairro;

/**
 *
 * @author petrovick
 */
@Stateless
public class BairroService implements IBairroService
{
    
    @PersistenceContext
    EntityManager em;

    @Override
    public String salvar(Bairro entity) {
        try
        {
            if(entity.getIdBairro() != null)
                em.merge(entity);
            else
                em.persist(entity);
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
            return ex.getMessage();
        }
        return null;
    }

    @Override
    public String excluir(Bairro Idobj)
    {
        try
        {
            Bairro bairro = em.find(Bairro.class, Idobj.getIdBairro());
            em.remove(bairro);
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
            return ex.getMessage();
        }
        return null;
    }

    @Override
    public Bairro obter(Integer IdObj)
    {
        return em.find(Bairro.class, IdObj);
    }

    @Override
    public List<Bairro> listar() {
        TypedQuery<Bairro> bairroQuery = em.createQuery("select a from Bairro a ORDER BY a.bairro", Bairro.class);
        return bairroQuery.getResultList();
    }
}
