package managedbean;

import br.com.pi.entidade.Atribuicao;
import br.com.pi.report.ReportAtribuicao;
import br.com.pi.service.IAtribuicaoService;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.servlet.http.HttpServletResponse;
import util.MenssagemUtil;

/**
 *
 * @author Luis
 */
@Named(value = "atribuicaoManagedBean")
@RequestScoped
public class AtribuicaoManagedBean {
    
    @EJB
    private IAtribuicaoService atribuicaoService;
    
    private Atribuicao atribuicao;
    private Atribuicao atribuicaoSelecionado;
    private ReportAtribuicao reportAtribuicao;
    private List<Atribuicao> listaAtribuicao;
    private String atPesq;
    
    public AtribuicaoManagedBean(){
        atribuicao = new Atribuicao();
        reportAtribuicao = new ReportAtribuicao();
    }
    
    public List<Atribuicao> todos(){
        return atribuicaoService.listar();
    }
    
    public void novo(){
        this.atribuicao = new Atribuicao();
    }
    
    public void salvar(){
        String erro = atribuicaoService.salvar(atribuicao);
        if(erro == null){
            MenssagemUtil.addMensagemInfo("Atribuicao salva com sucesso!");
            novo();
        }else{
            MenssagemUtil.addMensagemError(erro);
        }
    }
    
    public void excluir(){
        String erro = atribuicaoService.excluir(atribuicaoSelecionado);
        if(erro == null){
            MenssagemUtil.addMensagemInfo("Atribuição excluida com sucesso!");
        }else{
            MenssagemUtil.addMensagemError(erro);
        }
    }
    
    public void atualizar(){
        atribuicao = atribuicaoSelecionado;
    }
    
    
    public void gerarRelatorioFiltrado() throws IOException
    {
        String caminho = FacesContext.getCurrentInstance().getExternalContext().getRealPath("/");
        
        reportAtribuicao = new ReportAtribuicao();
        
        String erro = reportAtribuicao.gerar(caminho,listarRel());

        if (erro == null)
        {
            ByteArrayOutputStream bytes = reportAtribuicao.getOutput();
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
    
    public List<Atribuicao> listarRel()
    {
        System.out.println("Entrou");
        //pacPesq = pacPesq == null ? "" : pacPesq;
        //if(pacPesq.length() == 1)
        //{
            System.out.println("Entrou no método");
            listaAtribuicao = atribuicaoService.listarRel(atPesq);
            //listaPaciente = listaPaciente.stream().filter(x -> x.getPessoa().getNome().contains("An")).map(x -> x).collect(Collectors.toList());
        //}
        /*
        else if(pacPesq.length() > 1)
        {
            return listaPaciente.stream()
                    .filter(x -> x.getPessoa().getNome().contains(pacPesq))
                    .map(x -> x).collect(Collectors.toList());
            //listaPaciente = listaPaciente.stream().filter(x -> x.getPessoa().getNome().contains(pacPesq)).map(x -> x).collect(Collectors.toList());
        }*/
        
        System.out.println("Passou");
        for(Atribuicao p : listaAtribuicao)
        {
            System.out.println("" + p.getDescricao());
        }
        return listaAtribuicao;
    }


    public Atribuicao getAtribuicao() {
        return atribuicao;
    }

    public void setAtribuicao(Atribuicao atribuicao) {
        this.atribuicao = atribuicao;
    }

    public Atribuicao getAtribuicaoSelecionado() {
        return atribuicaoSelecionado;
    }

    public void setAtribuicaoSelecionado(Atribuicao atribuicaoSelecionado) {
        this.atribuicaoSelecionado = atribuicaoSelecionado;
    }

    public List<Atribuicao> getListaAtribuicao() {
        return listaAtribuicao;
    }

    public void setListaAtribuicao(List<Atribuicao> listaAtribuicao) {
        this.listaAtribuicao = listaAtribuicao;
    }

    public String getAtPesq() {
        return atPesq;
    }

    public void setAtPesq(String pacPesq) {
        this.atPesq = pacPesq;
    }
    
}
