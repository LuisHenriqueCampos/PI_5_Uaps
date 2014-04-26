/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package br.com.pi.serviceimple;

import br.com.pi.service.IPostosaudeService;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import br.com.pi.entidade.Postosaude;
import java.util.List;
import javax.persistence.TypedQuery;

/**
 *
 * @author petrovick
 */
@Stateless
public class PostosaudeService implements IPostosaudeService
{
    
    @PersistenceContext
    EntityManager em;

    @Override
    public String salvar(Postosaude entity) {
        try
        {
            em.merge(entity);
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
            return ex.getMessage();
        }
        return null;
    }

    @Override
    public String excluir(Postosaude Idobj) {
        
        try
        {
            em.remove(Idobj);
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
            return ex.getMessage();
        }
        return null;
    }

    @Override
    public Postosaude obter(Integer IdObj)
    {
        return em.find(Postosaude.class, IdObj);
    }

    @Override
    public List<Postosaude> listar() {
        TypedQuery<Postosaude> postosaudeQuery = em.createQuery("select a from Postosaude a", Postosaude.class);
        return postosaudeQuery.getResultList();
    }
    
}
