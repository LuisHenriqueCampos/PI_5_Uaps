package managedbean;

import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;
import br.com.pi.entidade.Area;
import br.com.pi.entidade.Medicoenfermeira;
import br.com.pi.entidade.Postosaude;
import br.com.pi.service.IAreaService;
import java.util.List;
import util.MenssagemUtil;

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
        List<Area> ll = areaService.listar();
        return ll;
    }
    
    public void salvar()
    {
        String erro = areaService.salvar(area);
        
        if(erro == null){
            MenssagemUtil.addMensagemInfo("Área salva com sucesso!");
            area = new Area();
        }else{
            MenssagemUtil.addMensagemError(erro);
        }
    }
    
    public void novo()
    {
        area = new Area();
    }
    
    public void editar()
    {
        area = areaSelecionada;
    }

    public void excluir()
    {
        String erro = areaService.excluir(areaSelecionada);
        
        if(erro == null){
            MenssagemUtil.addMensagemInfo("Área excluída com sucesso!");
        }else{
            MenssagemUtil.addMensagemError(erro);
        }
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