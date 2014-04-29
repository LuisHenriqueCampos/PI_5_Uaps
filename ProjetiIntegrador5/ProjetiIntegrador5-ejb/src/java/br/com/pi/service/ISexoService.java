/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package br.com.pi.service;

import br.com.pi.entidade.Sexo;
import java.util.List;

/**
 *
 * @author petrovick
 */
public interface ISexoService extends ICrudGeneric<Sexo>
{
    public String salvar(Sexo entity);
    public String excluir(Sexo Idobj);    
    public Sexo obter(Integer IdObj);
    public List<Sexo> listar();
}
