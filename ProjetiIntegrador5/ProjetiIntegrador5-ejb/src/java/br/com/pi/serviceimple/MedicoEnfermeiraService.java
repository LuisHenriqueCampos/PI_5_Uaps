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
 * @author Luis
 */
@Stateless
public class MedicoEnfermeiraService implements IMedicoEnfermeiraService{
    
    @PersistenceContext
    private EntityManager em;

    @Override
    public List<Medicoenfermeira> listar() {
        TypedQuery<Medicoenfermeira> meQuery = em
                .createQuery("SELECT m FROM Medicoenfermeira m ORDER BY m.pessoa.nome",Medicoenfermeira.class);
        return meQuery.getResultList();
    }

    @Override
    public String salvar(Medicoenfermeira entity) {
        
        TypedQuery<Medicoenfermeira> meQuery = em
                .createQuery("SELECT m FROM Medicoenfermeira m WHERE m.registro = :parametro",Medicoenfermeira.class);
        meQuery.setParameter("parametro", entity.getRegistro());
        
        try{
            entity.getPessoa().setMedicoenfermeira(entity);
            
            if(entity.getPessoa().getIdPessoa() != null){
                em.merge(entity);
            }else if(entity.getPessoa().getIdPessoa() == null && meQuery.getResultList().isEmpty()){
                em.persist(entity);
            }else{
                return "Este registro já foi cadastrado";
            }
        }catch(Exception ex){
            ex.printStackTrace();
            return ex.getMessage();
        }
        return null;
    }

    @Override
    public String excluir(Medicoenfermeira IdObj) {
        try{
            Medicoenfermeira medicoenfermeira = em.find(Medicoenfermeira.class, IdObj.getPessoa().getIdPessoa());
            em.remove(medicoenfermeira);
        }catch(Exception ex){
            ex.printStackTrace();
            return ex.getMessage();
        }
        return null;
    }

    @Override
    public Medicoenfermeira obter(Integer IdObj) {
        return em.find(Medicoenfermeira.class, IdObj);
    }    

    @Override
    public List<Medicoenfermeira> listarPorAtribuicao(String atribuicao) {
        atribuicao = atribuicao == null ? "":atribuicao;
        
        TypedQuery<Medicoenfermeira> meQuery = em
                .createQuery("SELECT DISTINCT m FROM Medicoenfermeira m WHERE m.pessoa.nome like :desc",Medicoenfermeira.class);
        meQuery.setParameter("desc", "%"+atribuicao+"%");
        return meQuery.getResultList();
    }
    
}
