package br.com.pi.managedbean;

import br.com.pi.entidade.Area;
import br.com.pi.service.IMicroareaService;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;
import br.com.pi.entidade.Microarea;
import java.util.List;
/**
 *
 * @author petrovick
 */
@Named(value = "microareaManagedBean")
@RequestScoped
public class MicroareaManagedbean
{
    @EJB
    IMicroareaService microareaService;
    private Microarea microarea;
    private Microarea microareaSelecionada;
    
    public MicroareaManagedbean()
    {
        microarea = new Microarea();
        microarea.setIdArea(new Area());
    }
    
    public void excluir()
    {
        microareaService.excluir(microareaSelecionada);
    }
    
    public void salvar()
    {
        System.out.println("AreaID:" + microarea.getIdArea());
        microareaService.salvar(microarea);
    }
    
    public void editar()
    {
        microarea = microareaSelecionada;
    }
    
    public void novo()
    {
        microarea = new Microarea();
        microarea.setIdArea(new Area());
    }
    
    public List<Microarea> todos()
    {
        return microareaService.listar();
    }

    public Microarea getMicroarea() {
        return microarea;
    }

    public void setMicroarea(Microarea microarea) {
        this.microarea = microarea;
    }

    public Microarea getMicroareaSelecionada() {
        return microareaSelecionada;
    }

    public void setMicroareaSelecionada(Microarea microareaSelecionada) {
        this.microareaSelecionada = microareaSelecionada;
    }
    
    
}
