/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package br.com.pi.service;

import br.com.pi.entidade.Atribuicao;
import java.util.List;

/**
 *
 * @author Luis
 */
public interface IAtribuicaoService extends ICrudGeneric<Atribuicao>{
    
    public String salvar(Atribuicao entity);
    public String excluir(Atribuicao Idobj);    
    public Atribuicao obter(Integer IdObj);
    public List<Atribuicao> listar();
    
}
