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
    public String salvar(Medicoenfermeira entity) {
        entity.setAssinatura(entity.getPessoa().getNome()+" - "+entity.getRegistro());
        
        try
        {
            if(entity.getPessoa().getIdPessoa()!=null){
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
    public String excluir(Medicoenfermeira Idobj)
    {        
        try
        {
            Medicoenfermeira mef = em.find(Medicoenfermeira.class, Idobj.getPessoa().getIdPessoa());
            em.remove(mef);
        }
        catch(Exception ex)
        {
            throw ex;
        }
        return null;
    }

    @Override
    public Medicoenfermeira obter(Integer IdObj) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Medicoenfermeira> listar() {
        TypedQuery<Medicoenfermeira> mefQuery = em.createQuery("select mef from Medicoenfermeira mef", Medicoenfermeira.class);
        return mefQuery.getResultList();
    }

    @Override
    public List<Medicoenfermeira> listarPorAtribuicao(String atribuicao)
    {
        TypedQuery<Medicoenfermeira> mefQuery = em.createQuery("select mef from Medicoenfermeira mef where mef.idAtribuicao.descricao like :descAtribuicao", Medicoenfermeira.class);
        mefQuery.setParameter("descAtribuicao", "%" + atribuicao + "%");
        return mefQuery.getResultList();
    }
    
}
