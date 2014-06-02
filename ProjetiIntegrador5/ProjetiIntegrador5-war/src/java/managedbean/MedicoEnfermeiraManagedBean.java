package managedbean;

import br.com.pi.entidade.Atribuicao;
import br.com.pi.service.IMedicoEnfermeiraService;
import br.com.pi.entidade.Medicoenfermeira;
import br.com.pi.entidade.Pessoaa;
import br.com.pi.report.ReportFamilia;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.servlet.http.HttpServletResponse;
import br.com.pi.report.ReportMedico;
import util.MenssagemUtil;
/**
 *
 * @author Luis
 */
@Named(value = "medicoEnfermeiraManagedBean")
@RequestScoped
public class MedicoEnfermeiraManagedBean
{
    @EJB
    IMedicoEnfermeiraService medicoEnfermeiraService;
    private Medicoenfermeira medicoEnfermeira;
    private List<Medicoenfermeira> medicoEnfermeiras;
    private Medicoenfermeira medicoenfermeiraSelecionado;
    
    private ReportMedico reportMedico;
    
    public MedicoEnfermeiraManagedBean()
    {
        medicoEnfermeira = new Medicoenfermeira();
        medicoEnfermeira.setIdAtribuicao(new Atribuicao());
        medicoEnfermeira.setPessoa(new Pessoaa());
    }
    
    public void novo()
    {
        medicoEnfermeira = new Medicoenfermeira();
    }
    
    public void editar()
    {
        medicoEnfermeira = medicoenfermeiraSelecionado;
    }
    
    public void excluir()
    {
        String erro = medicoEnfermeiraService.excluir(medicoenfermeiraSelecionado);
        
        if(erro == null){
            MenssagemUtil.addMensagemInfo("Cadastro excluído com sucesso!");
        }else{
            MenssagemUtil.addMensagemError(erro);
        }
    }
    
    public void salvar()
    {
        String erro = medicoEnfermeiraService.salvar(medicoEnfermeira);
        
        if(erro == null){
            MenssagemUtil.addMensagemInfo("Cadastro salvo com sucesso!");
        }else{
            MenssagemUtil.addMensagemError(erro);
        }
    }
    
    public List<Medicoenfermeira> listar()
    {
        return medicoEnfermeiraService.listar();
    }
    
    public List<Medicoenfermeira> listarMedico()
    {
        return medicoEnfermeiraService.listarPorAtribuicao("Medico");
    }
    
    public List<Medicoenfermeira> listarEnfermeira()
    {
        return medicoEnfermeiraService.listarPorAtribuicao("Enfermeira");
    }

    public void gerar() throws IOException
    {
        String caminho = FacesContext.getCurrentInstance().getExternalContext().getRealPath("/");
        
        reportMedico = new ReportMedico();
        
        String erro = reportMedico.gerar(caminho,listar());
        
        if (erro == null)
        {
            ByteArrayOutputStream bytes = reportMedico.getOutput();
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
    
    
    public Medicoenfermeira getMedicoEnfermeira() {
        return medicoEnfermeira;
    }

    public void setMedicoEnfermeira(Medicoenfermeira medicoEnfermeira) {
        this.medicoEnfermeira = medicoEnfermeira;
    }

    public List<Medicoenfermeira> getMedicoEnfermeiras() {
        return medicoEnfermeiras;
    }

    public void setMedicoEnfermeiras(List<Medicoenfermeira> medicoEnfermeiras) {
        this.medicoEnfermeiras = medicoEnfermeiras;
    }

    public Medicoenfermeira getMedicoenfermeiraSelecionado() {
        return medicoenfermeiraSelecionado;
    }

    public void setMedicoenfermeiraSelecionado(Medicoenfermeira medicoenfermeiraSelecionado) {
        this.medicoenfermeiraSelecionado = medicoenfermeiraSelecionado;
    }
    
    
    
    
}
