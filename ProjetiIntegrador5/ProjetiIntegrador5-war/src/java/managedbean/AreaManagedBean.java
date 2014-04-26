package br.com.pi.managedbean;

import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;
import br.com.pi.entidade.Area;
import br.com.pi.entidade.Medicoenfermeira;
import br.com.pi.entidade.Postosaude;
import br.com.pi.service.IAreaService;
import java.util.List;

/**
 *
 * @author petrovick
 */
@Named(value = "areaManagedBean")
@RequestScoped
public class AreaManagedBean
{
    @EJB
    IAreaService areaService;
    private Area area;
    private Area areaSelecionada;
    private Postosaude postosaudeSelecionado;
    
    public AreaManagedBean()
    {
        area = new Area();
        area.setIdPostoSaude(new Postosaude());
        area.setMedicoenfermeiraidEnfermeira(new Medicoenfermeira());
        area.setMedicoenfermeiraidMedico(new Medicoenfermeira());
    }
    
    public List<Postosaude> listaPostosaude()
    {
        return null;
    }
    
    public List<Area> todos()
    {
        return null;
    }
    
    public List<Area> salvar()
    {
        return null;
    }
    
    public List<Area> novo()
    {
        return null;
    }

    public Area getArea() {
        return area;
    }

    public void setArea(Area area) {
        this.area = area;
    }

    public Area getAreaSelecionada() {
        return areaSelecionada;
    }

    public void setAreaSelecionada(Area areaSelecionada) {
        this.areaSelecionada = areaSelecionada;
    }
    
}
