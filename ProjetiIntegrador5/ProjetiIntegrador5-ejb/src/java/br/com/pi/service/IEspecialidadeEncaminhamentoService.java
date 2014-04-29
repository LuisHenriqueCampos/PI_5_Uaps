/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package br.com.pi.service;

import br.com.pi.entidade.EspecialidadeEncaminhamento;
import java.util.List;

/**
 *
 * @author petrovick
 */
public interface IEspecialidadeEncaminhamentoService extends ICrudGeneric<EspecialidadeEncaminhamento>
{
    public String salvar(EspecialidadeEncaminhamento entity);
    public String excluir(EspecialidadeEncaminhamento Idobj);    
    public EspecialidadeEncaminhamento obter(Integer IdObj);
    public List<EspecialidadeEncaminhamento> listar();
    
}
