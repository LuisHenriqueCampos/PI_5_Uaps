/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package br.com.pi.service;

import br.com.pi.entidade.Tipoencaminhamento;
import br.com.pi.model.GraficoTipoEncaminhamentoModel;
import java.util.List;

/**
 *
 * @author petrovick
 */

public interface ITipoEncaminhamentoService extends ICrudGeneric<Tipoencaminhamento>
{
    public String salvar(Tipoencaminhamento entity);
    public String excluir(Tipoencaminhamento Idobj);    
    public Tipoencaminhamento obter(Short IdObj);
    default Tipoencaminhamento obter(Integer i)
    {
        System.out.println("nao usar");
        return null;
    }
    public List<Tipoencaminhamento> listar();
    
    public List<GraficoTipoEncaminhamentoModel> consultaGrafico();
}
