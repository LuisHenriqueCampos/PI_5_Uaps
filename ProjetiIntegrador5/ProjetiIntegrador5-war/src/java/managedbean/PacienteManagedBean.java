package managedbean;

import br.com.pi.entidade.Familia;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;
import br.com.pi.entidade.Paciente;
import br.com.pi.entidade.Pessoaa;
import br.com.pi.entidade.Sexo;
import br.com.pi.report.ReportPaciente;
import br.com.pi.service.IPacienteService;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletResponse;
import util.MenssagemUtil;
/**
 *
 * @author petrovick
 */
@Named(value = "pacienteManagedBean")
@RequestScoped
public class PacienteManagedBean
{
    @EJB
    private IPacienteService pacienteService;
    private Paciente paciente;
    private Paciente pacienteSelecionado;
    
    private ReportPaciente reportPaciente;
    private List<Paciente> listaPaciente;
    private String pacPesq;
    
    
    public PacienteManagedBean()
    {
        paciente = new Paciente();
        paciente.setPessoa(new Pessoaa());
        paciente.setIdSexo(new Sexo());
        paciente.setIdFamilia(new Familia());
        listaPaciente = new ArrayList<>();
        pacPesq = "";
    }
    
    public void salvar()
    {
       String erro = pacienteService.salvar(paciente);
       
       if(erro == null){
           MenssagemUtil.addMensagemInfo("Paciente salvo com sucesso!");
           paciente = new Paciente();
       }else{
           MenssagemUtil.addMensagemError(erro);
       }
    }
    
    public List<Paciente> listar()
    {
        return pacienteService.listar();
    }
    
    public List<Paciente> listarRel()
    {
        listaPaciente = pacienteService.listarRel(pacPesq);

        for(Paciente p : listaPaciente)
        {
            System.out.println("" + p.getPessoa().getNome());
        }
        return listaPaciente;
    }
    
    public void excluir()
    {
        String erro = pacienteService.excluir(pacienteSelecionado);
        
        if(erro == null){
            MenssagemUtil.addMensagemInfo("Paciente excluído com sucesso!");
        }else{
            MenssagemUtil.addMensagemError(erro);
        }
    }
    
    public void editar()
    {
        this.paciente = pacienteSelecionado;
    }
    
    public void novo()
    {
        this.paciente = new Paciente();
    }
    
    public void gerarRelatorioFiltrado() throws IOException
    {
        String caminho = FacesContext.getCurrentInstance().getExternalContext().getRealPath("/");
        
        reportPaciente = new ReportPaciente();
        
        String erro = reportPaciente.gerar(caminho,listarRel());

        if (erro == null)
        {
            ByteArrayOutputStream bytes = reportPaciente.getOutput();
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

    public void gerar() throws IOException
    {
        String caminho = FacesContext.getCurrentInstance().getExternalContext().getRealPath("/");
        
        reportPaciente = new ReportPaciente();
        
        String erro = reportPaciente.gerar(caminho,listar());

        if (erro == null)
        {
            ByteArrayOutputStream bytes = reportPaciente.getOutput();
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
    
    public Paciente getPaciente() {
        return paciente;
    }

    public void setPaciente(Paciente paciente) {
        this.paciente = paciente;
    }

    public Paciente getPacienteSelecionado() {
        return pacienteSelecionado;
    }

    public void setPacienteSelecionado(Paciente pacienteSelecionado) {
        this.pacienteSelecionado = pacienteSelecionado;
    }

    public List<Paciente> getListaPaciente() {
        return listaPaciente;
    }

    public void setListaPaciente(List<Paciente> listaPaciente) {
        this.listaPaciente = listaPaciente;
    }

    public String getPacPesq() {
        return pacPesq;
    }

    public void setPacPesq(String pacPesq) {
        this.pacPesq = pacPesq;
    }
}
