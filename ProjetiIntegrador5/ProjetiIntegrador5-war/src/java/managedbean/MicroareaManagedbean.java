package managedbean;

import br.com.pi.entidade.Area;
import br.com.pi.entidade.Microarea;
import br.com.pi.entidade.Postosaude;
import br.com.pi.report.ReportFamilia;
import br.com.pi.report.ReportMicroarea;
import br.com.pi.service.IAreaService;
import br.com.pi.service.IMicroareaService;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.view.ViewScoped;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.servlet.http.HttpServletResponse;
import util.MenssagemUtil;
/**
 *
 * @author petrovick
 */
@Named(value = "microareaManagedBean")
@ViewScoped
public class MicroareaManagedbean
{
    @EJB
    IMicroareaService microareaService;
    
    @EJB
    IAreaService areaService;
    
    private Microarea microarea;
    private Microarea microareaSelecionada;
    private Postosaude postosaude;
    private List<Area> areas;
    
    private ReportMicroarea reportMicroarea;
    
    public MicroareaManagedbean()
    {
        microarea = new Microarea();
    }
    
     @PostConstruct
    public void init(){
        
        String id= FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("id");
	
	if(id != null){
            microarea = microareaService.obter(Integer.parseInt(id));
            postosaude = microarea.getIdArea().getIdPostoSaude();
            areas = areaService.listarPorPosto(postosaude);
	} 
    }
    
    public void excluir()
    {
        String erro = microareaService.excluir(microareaSelecionada);
        if(erro==null){
            MenssagemUtil.addMensagemInfo("Micro-Área excluída com sucesso!");
        }else{
            MenssagemUtil.addMensagemError(erro);
        }
    }
    
    public void salvar()
    {
        String erro = microareaService.salvar(microarea);
        if(erro==null){
            MenssagemUtil.addMensagemInfo("Micro-Área salva com sucesso!");
            microarea = new Microarea();
        }else{
            MenssagemUtil.addMensagemError(erro);
        }
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
    
    public void listarPorPosto(){
        if(postosaude != null){
            areas = areaService.listarPorPosto(postosaude);
        }
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

    public Postosaude getPostosaude() {
        return postosaude;
    }

    public void setPostosaude(Postosaude postosaude) {
        this.postosaude = postosaude;
    }

    public List<Area> getAreas() {
        return areas;
    }    
    
}
