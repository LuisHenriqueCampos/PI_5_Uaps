package managedbean;

import br.com.pi.entidade.Area;
import br.com.pi.service.IMicroareaService;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;
import br.com.pi.entidade.Microarea;
import br.com.pi.report.ReportFamilia;
import br.com.pi.report.ReportMicroarea;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletResponse;
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
    
    private ReportMicroarea reportMicroarea;
    
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
    
    public void gerar() throws IOException
    {
        String caminho = FacesContext.getCurrentInstance().getExternalContext().getRealPath("/");
        
        reportMicroarea = new ReportMicroarea();
        
        String erro = reportMicroarea.gerar(caminho,todos());

        if (erro == null)
        {
            ByteArrayOutputStream bytes = reportMicroarea.getOutput();
            HttpServletResponse res = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
            res.setContentType("application/pdf");
            res.setHeader("Content-disposition", "inline;filename=relatorio.pdf");
            res.getOutputStream().write(bytes.toByteArray());
            res.getCharacterEncoding();
            FacesContext.getCurrentInstance().responseComplete();

            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Relatório gerado com sucesso!", null));
        }
        
        else
        {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, erro, null));
        }
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
