package managedbean;

import br.com.pi.service.IPostosaudeService;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;
import br.com.pi.entidade.Postosaude;
import br.com.pi.report.ReportPostoSaude;
import java.awt.event.ActionEvent;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletResponse;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;
import util.MenssagemUtil;

/**
 *
 * @author petrovick
 */
@Named(value = "postoSaudeManagedBean")
@RequestScoped
public class PostoSaudeManagedBean {

    @EJB
    IPostosaudeService postoService;
    private Postosaude postoSaude;
    private Postosaude postosaudeSelecionado;

    private ReportPostoSaude reportPostoSaude;

    public PostoSaudeManagedBean() {
        postoSaude = new Postosaude();
    }

    public void editar() {
        postoSaude = postosaudeSelecionado;
    }

    public void excluir() {
        String erro = postoService.excluir(postosaudeSelecionado);

        if (erro == null) {
            MenssagemUtil.addMensagemInfo("Posto de Saúde excluído com sucesso!");
        } else {
            MenssagemUtil.addMensagemError(erro);
        }
    }

    public void salvar() {
        String erro = postoService.salvar(postoSaude);

        if (erro == null) {
            MenssagemUtil.addMensagemInfo("Posto de Saúde salvo com sucesso!");
            this.postoSaude = new Postosaude();
        } else {
            MenssagemUtil.addMensagemError(erro);
        }

    }

    public void novo() {
        this.postoSaude = new Postosaude();
    }

    public List<Postosaude> todos() {
        List<Postosaude> postos = postoService.listar();
        for (Postosaude p : postos) {
            System.out.println("" + p.getNomePosto());
        }
        return postoService.listar();
    }

    public Postosaude getPostoSaude() {
        return postoSaude;
    }

    public void setPostoSaude(Postosaude postoSaude) {
        this.postoSaude = postoSaude;
    }

    public Postosaude getPostosaudeSelecionado() {
        return postosaudeSelecionado;
    }

    public void setPostosaudeSelecionado(Postosaude postosaudeSelecionado) {
        this.postosaudeSelecionado = postosaudeSelecionado;
    }

    public void gerar() throws IOException
    {
        String caminho = FacesContext.getCurrentInstance().getExternalContext().getRealPath("/");
        
        System.out.println("\n\n\n\n**********CAMINHO = " + caminho);
        
        reportPostoSaude = new ReportPostoSaude();
        
        String erro = reportPostoSaude.gerar(caminho,todos());

        if (erro == null)
        {
            ByteArrayOutputStream bytes = reportPostoSaude.getOutput();
            HttpServletResponse res = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
            res.setContentType("application/pdf");
            res.setHeader("Content-disposition", "inline;filename=alunos.pdf");
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
}
