package managedbean;

import br.com.pi.entidade.Endereco;
import br.com.pi.service.IFamiliaService;
import javax.ejb.EJB;
import javax.inject.Named;
import br.com.pi.entidade.Familia;
import br.com.pi.report.ReportPostoSaude;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletResponse;
import br.com.pi.report.ReportFamilia;
/**
 *
 * @author petrovick
 */
@Named(value = "familiaManagedBean")
@RequestScoped
public class FamiliaManagedBean
{
    @EJB
    IFamiliaService familiaService;
    private Familia familia;
    private Familia familiaSelecionada;
    
    private ReportFamilia reportFamilia;
    
    public FamiliaManagedBean()
    {
        familia = new Familia();
        familia.setIdEndereco(new Endereco());
    }
    
    public void salvar()
    {
        System.out.println("Entrou no salvar()");
        familiaService.salvar(familia);
    }
    
    public void excluir()
    {
        familiaService.excluir(familiaSelecionada);
    }
    
    public void novo()
    {
        familia = new Familia();
        familia.setIdEndereco(new Endereco());
    }
    
    public void editar()
    {
        familia = familiaSelecionada;
    }
    
    public List<Familia> listar()
    {
        return familiaService.listar();
    }

    public void gerar() throws IOException
    {
        String caminho = FacesContext.getCurrentInstance().getExternalContext().getRealPath("/");
        
        reportFamilia = new ReportFamilia();
        
        String erro = reportFamilia.gerar(caminho,listar());

        if (erro == null)
        {
            ByteArrayOutputStream bytes = reportFamilia.getOutput();
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
    
    public Familia getFamilia() {
        return familia;
    }

    public void setFamilia(Familia familia) {
        this.familia = familia;
    }

    public Familia getFamiliaSelecionada() {
        return familiaSelecionada;
    }

    public void setFamiliaSelecionada(Familia familiaSelecionada) {
        this.familiaSelecionada = familiaSelecionada;
    }
}
