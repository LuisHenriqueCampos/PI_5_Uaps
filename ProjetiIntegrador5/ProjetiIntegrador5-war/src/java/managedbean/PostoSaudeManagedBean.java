package managedbean;

import br.com.pi.service.IPostosaudeService;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;
import br.com.pi.entidade.Postosaude;
import br.com.pi.report.ReportPostoSaude;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.*;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletResponse;
import util.MenssagemUtil;

@Named(value = "postoSaudeManagedBean")
@RequestScoped
public class PostoSaudeManagedBean {

    @EJB
    IPostosaudeService postoService;
    private Postosaude postoSaude;
    private Postosaude postosaudeSelecionado;

    private ReportPostoSaude reportPostoSaude;
    private List<Postosaude> listaPostosaude;
    private String posPesq;

    public PostoSaudeManagedBean() {
        postoSaude = new Postosaude();
        listaPostosaude = new ArrayList<>();
        posPesq = "";
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
    
    public List<Postosaude> listarRel() 
    {        
        listaPostosaude = postoService.listarRel(posPesq);

        for(Postosaude p : listaPostosaude)
        {
            System.out.println("" + p.getNomePosto());
        }
        return listaPostosaude;
    }
    
    public void gerarRelatorioFiltrado() throws IOException {
        String caminho = FacesContext.getCurrentInstance().getExternalContext().getRealPath("/");
        
        reportPostoSaude = new ReportPostoSaude();
        
        String erro = reportPostoSaude.gerar(caminho,listarRel());

        if (erro == null)
        {
            ByteArrayOutputStream bytes = reportPostoSaude.getOutput();
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
    
    public void gerar() throws IOException {
        String caminho = FacesContext.getCurrentInstance().getExternalContext().getRealPath("/");
        
        reportPostoSaude = new ReportPostoSaude();
        
        String erro = reportPostoSaude.gerar(caminho,todos());

        if (erro == null)
        {
            ByteArrayOutputStream bytes = reportPostoSaude.getOutput();
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
    
    public List<Postosaude> getListaPostosaude() {
        return listaPostosaude;
    }

    public void setListaPostosaude(List<Postosaude> listaPostosaude) {
        this.listaPostosaude = listaPostosaude;
    }

    public String getPosPesq() {
        return posPesq;
    }

    public void setPosPesq(String posPesq) {
        this.posPesq = posPesq;
    }

}
