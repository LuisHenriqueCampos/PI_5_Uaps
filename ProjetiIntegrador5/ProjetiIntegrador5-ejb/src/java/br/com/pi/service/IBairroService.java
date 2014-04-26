/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package br.com.pi.service;

import br.com.pi.entidade.Bairro;
import java.util.List;

/**
 *
 * @author petrovick
 */
public interface IBairroService extends ICrudGeneric<Bairro>
{
    public String salvar(Bairro entity);
    public String excluir(Bairro Idobj);    
    public Bairro obter(Integer IdObj);
    public List<Bairro> listar();
}
