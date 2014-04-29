/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package managedbean;

import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;
import br.com.pi.entidade.Tipoencaminhamento;
import br.com.pi.service.ITipoEncaminhamentoService;
import java.util.List;
/**
 *
 * @author petrovick
 */
@Named(value = "tipoEncaminhamentoManagedBean")
@RequestScoped
public class TipoEncaminhamentoManagedBean
{
    @EJB
    ITipoEncaminhamentoService tipoEncaminhamentoService;
    private Tipoencaminhamento tipoEncaminhamento;
    private Tipoencaminhamento tipoEncaminhamentoSeTipoencaminhamento;
    
    public TipoEncaminhamentoManagedBean()
    {
        tipoEncaminhamento = new Tipoencaminhamento();
    }
    
    public List<Tipoencaminhamento> listar()
    {
        return tipoEncaminhamentoService.listar();
    }

    public Tipoencaminhamento getTipoEncaminhamento() {
        return tipoEncaminhamento;
    }

    public void setTipoEncaminhamento(Tipoencaminhamento tipoEncaminhamento) {
        this.tipoEncaminhamento = tipoEncaminhamento;
    }

    public Tipoencaminhamento getTipoEncaminhamentoSeTipoencaminhamento() {
        return tipoEncaminhamentoSeTipoencaminhamento;
    }

    public void setTipoEncaminhamentoSeTipoencaminhamento(Tipoencaminhamento tipoEncaminhamentoSeTipoencaminhamento) {
        this.tipoEncaminhamentoSeTipoencaminhamento = tipoEncaminhamentoSeTipoencaminhamento;
    }
    
    
}
