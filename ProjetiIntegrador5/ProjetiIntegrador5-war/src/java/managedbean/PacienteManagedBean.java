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
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletResponse;
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
    
    public PacienteManagedBean()
    {
        paciente = new Paciente();
        paciente.setPessoa(new Pessoaa());
        paciente.setIdSexo(new Sexo());
        paciente.setIdFamilia(new Familia());
    }
    
    public void salvar()
    {
        try
        {
            pacienteService.salvar(paciente);
        }
        catch(Exception ex)
        {
            throw ex;
        }
    }
    
    public List<Paciente> listar()
    {
        return pacienteService.listar();
    }
    
    public void excluir()
    {
        pacienteService.excluir(pacienteSelecionado);
    }
    
    public void editar()
    {
        this.paciente = pacienteSelecionado;
    }
    
    public void novo()
    {
        this.paciente = new Paciente();
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
}
