/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package br.com.pi.service;

import br.com.pi.entidade.Familia;
import java.util.List;

/**
 *
 * @author petrovick
 */
public interface IFamiliaService extends ICrudGeneric<Familia>
{
    public String salvar(Familia entity);
    public String excluir(Familia Idobj);    
    public Familia obter(Integer IdObj);
    public List<Familia> listar();
    public List<Familia> listarFamiliaParaAutoComplete(String familia);
}
