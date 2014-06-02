/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package managedbean;

import br.com.pi.entidade.Tipoencaminhamento;
import br.com.pi.model.GraficoTipoEncaminhamentoModel;
import br.com.pi.service.ITipoEncaminhamentoService;
import java.util.List;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;
import org.primefaces.model.chart.CartesianChartModel;
import org.primefaces.model.chart.ChartSeries;
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
    
     public CartesianChartModel gerarGrafico(){
        CartesianChartModel cartesian =  new CartesianChartModel();
        
        List<GraficoTipoEncaminhamentoModel> models =
                tipoEncaminhamentoService.consultaGrafico();            
        
        for(GraficoTipoEncaminhamentoModel model : models){
            ChartSeries cs = new ChartSeries();
            cs.setLabel(model.getNomeTipo());
            cs.set(model.getNomeTipo(), model.getQuantidade());
            cartesian.addSeries(cs);
        }        
        return cartesian;
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
