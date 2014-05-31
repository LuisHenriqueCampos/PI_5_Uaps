

package managedbean;

import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;
import br.com.pi.service.*;
import br.com.pi.entidade.*;
import br.com.pi.report.ReportBairro;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletResponse;
import util.MenssagemUtil;
/**
 *
 * @author petrovick
 */
@Named(value = "bairroManagedBean")
@RequestScoped
public class BairroManagedBean
{
    @EJB
    IBairroService bairroService;
    private Bairro bairroSelecionado;
    private Bairro bairro;
    private ReportBairro reportBairro;
    private List<Bairro> listaBairro;
    private String bairroPesq;
    
    public BairroManagedBean()
    {
        bairro = new Bairro();
        reportBairro = new ReportBairro();
    }
    
    public void editar()
    {
        bairro = bairroSelecionado;        
    }
    
    public void novo()
    {
        bairro = new Bairro();
    }
    
    public void salvar()
    {
        String erro = bairroService.salvar(bairro);
        
        if(erro == null){
            MenssagemUtil.addMensagemInfo("Bairro salvo com sucesso!");
            this.bairro = new Bairro();
        }else{
            MenssagemUtil.addMensagemError(erro);
        }
        
    }
    
    public List<Bairro> todos()
    {
        return bairroService.listar();
    }
    
    public void excluir()
    {
        String erro = bairroService.excluir(bairroSelecionado);
        
        if(erro == null)
            MenssagemUtil.addMensagemInfo("Bairro excluído com sucesso!");
        else
            MenssagemUtil.addMensagemError(erro);
        
    }
    
    public void gerarRelatorioFiltrado() throws IOException
    {
        String caminho = FacesContext.getCurrentInstance().getExternalContext().getRealPath("/");
        
        reportBairro = new ReportBairro();
        
        String erro = reportBairro.gerar(caminho,listarRel());

        if (erro == null)
        {
            ByteArrayOutputStream bytes = reportBairro.getOutput();
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
    
    public List<Bairro> listarRel()
    {
        System.out.println("Entrou");
        //pacPesq = pacPesq == null ? "" : pacPesq;
        //if(pacPesq.length() == 1)
        //{
        
        
            System.out.println("Entrou no método");
        listaBairro = bairroService.listarRel(bairroPesq);
        //bairroService.listar().forEach(x -> x.getBairro().equals("ca"));

            
            //listaPaciente = listaPaciente.stream().filter(x -> x.getPessoa().getNome().contains("An")).map(x -> x).collect(Collectors.toList());
        //}
/*    
        else if(pacPesq.length() > 1)
        {
            return listaPaciente.stream()
                    .filter(x -> x.getPessoa().getNome().contains(pacPesq))
                    .map(x -> x).collect(Collectors.toList());
            //listaPaciente = listaPaciente.stream().filter(x -> x.getPessoa().getNome().contains(pacPesq)).map(x -> x).collect(Collectors.toList());
        }
*/
        System.out.println("Passou");
        for(Bairro b : listaBairro)
        {
            System.out.println("" + b.getBairro());
        }
        return listaBairro;
    }
    
    public Bairro getBairroSelecionado() {
        return bairroSelecionado;
    }
    
    

    public void setBairroSelecionado(Bairro bairroSelecionado) {
        this.bairroSelecionado = bairroSelecionado;
    }

    public Bairro getBairro() {
        return bairro;
    }

    public void setBairro(Bairro bairro) {
        this.bairro = bairro;
    }

    public List<Bairro> getListaBairro() {
        return listaBairro;
    }

    public void setListaBairro(List<Bairro> listaBairro) {
        this.listaBairro = listaBairro;
    }

    public String getBairroPesq() {
        return bairroPesq;
    }

    public void setBairroPesq(String bairroPesq) {
        this.bairroPesq = bairroPesq;
    }
    
    
}
