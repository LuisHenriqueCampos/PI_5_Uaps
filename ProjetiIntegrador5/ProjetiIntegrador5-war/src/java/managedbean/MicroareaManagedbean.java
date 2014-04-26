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
    
    public void excluir()
    {
        
    }
    
    public void salvar()
    {
        
    }
    
    public void editar()
    {
        
    }
    
    public List<Microarea> todos()
    {
        return microareaService.listar();
    }
    
    public MicroareaManagedbean()
    {
        microarea = new Microarea();
        microarea.setIdArea(new Area());
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
