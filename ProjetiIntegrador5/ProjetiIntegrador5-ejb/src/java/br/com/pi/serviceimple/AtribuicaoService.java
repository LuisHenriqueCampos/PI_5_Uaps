/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package br.com.pi.serviceimple;

import br.com.pi.entidade.Atribuicao;
import br.com.pi.service.IAtribuicaoService;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

/**
 *
 * @author Luis
 */
@Stateless
public class AtribuicaoService implements IAtribuicaoService{
    
    @PersistenceContext
    private EntityManager em;

    @Override
    public List<Atribuicao> listar() {
        TypedQuery<Atribuicao> atribuicaoQuery = 
                em
                .createQuery("SELECT a FROM Atribuicao a ORDER BY a.descricao",Atribuicao.class);
        return atribuicaoQuery.getResultList();
    }

    @Override
    public String salvar(Atribuicao entity) {
        
        TypedQuery<Atribuicao> atribuicaoQuery = em
                .createQuery("SELECT a FROM Atribuicao a WHERE a.descricao = :desc",Atribuicao.class);
        
        atribuicaoQuery.setParameter("desc", entity.getDescricao());
        
       try{
            if(entity.getIdAtribuicao()!= null){
                em.merge(entity);
            }else if(entity.getIdAtribuicao() == null && atribuicaoQuery.getResultList().isEmpty()){
                em.persist(entity);
            }else{
                return "Atribuição já Cadastrada";
            }
        }catch(Exception ex){
            ex.printStackTrace();
            return ex.getMessage();
        }
        return null;
    }

    @Override
    public String excluir(Atribuicao IdObj) {
        try{
            Atribuicao atribuicao = em.find(Atribuicao.class, IdObj.getIdAtribuicao());
            em.remove(atribuicao);
        }catch(Exception ex){
            ex.printStackTrace();
            return ex.getMessage();
        }
        return null;
    }

    @Override
    public Atribuicao obter(Integer IdObj) {
        
        return em.find(Atribuicao.class, IdObj);
    }
    
}
