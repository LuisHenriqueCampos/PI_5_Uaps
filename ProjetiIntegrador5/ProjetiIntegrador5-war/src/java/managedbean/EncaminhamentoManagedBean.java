package managedbean;

import br.com.pi.entidade.Encaminhamento;
import br.com.pi.entidade.EspecialidadeEncaminhamento;
import br.com.pi.entidade.Medicoenfermeira;
import br.com.pi.entidade.Tipoencaminhamento;
import br.com.pi.entidade.Paciente;
import br.com.pi.report.ReportEncaminhamento;
import br.com.pi.service.IEncaminhamentoService;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author petrovick
 */
@Named(value = "encaminhamentoManagedBean")
@RequestScoped
public class EncaminhamentoManagedBean
{
    @EJB
    IEncaminhamentoService encaminhamentoService;
    private Encaminhamento encaminhamento;
    private Encaminhamento encaminhamentoSelecionado;
    
    private ReportEncaminhamento reportEncaminhamento;
    private List<Encaminhamento> listaEncaminhamento;
    private String pesquisaDataInicio;
    private String pesquisaDataFim;
    
    public EncaminhamentoManagedBean()
    {
        novo();
        reportEncaminhamento = new ReportEncaminhamento();
    }
    
    public void salvar()
    {
        try
        {
            encaminhamentoService.salvar(encaminhamento);
            novo();
        }
        catch(Exception ex)
        {
            System.out.println("" + ex.getMessage());
        }
    }
    
    public void save()
    {
        try
        {
            System.out.println("Salvar no controller");
            
            System.out.println("Salvou");
        }
        catch(Exception ex)
        {
            System.out.println("" + ex.getMessage());
        }
    }
    
    public List<Encaminhamento> listar()
    {
        return encaminhamentoService.listar();
    }
    
    public void excluir()
    {
        encaminhamentoService.excluir(encaminhamentoSelecionado);
    }
    
    public void novo()
    {
        encaminhamento = new Encaminhamento();
        encaminhamento.setIdEspecialidade(new EspecialidadeEncaminhamento());
        encaminhamento.setIdPessoaMedicoEnfermeira(new Medicoenfermeira());
        encaminhamento.setIdPessoaPaciente(new Paciente());
        encaminhamento.setIdtipoEncaminhamento(new Tipoencaminhamento());
    }
    
    public void editar()
    {
        encaminhamento = encaminhamentoSelecionado;
    }
    
    public void gerar() throws IOException
    {
        String caminho = FacesContext.getCurrentInstance().getExternalContext().getRealPath("/");
        
        reportEncaminhamento = new ReportEncaminhamento();
        
        String erro = reportEncaminhamento.gerar(caminho,listar());

        if (erro == null)
        {
            ByteArrayOutputStream bytes = reportEncaminhamento.getOutput();
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
    
    public void gerarRelatorioFiltrado() throws IOException
    {
        String caminho = FacesContext.getCurrentInstance().getExternalContext().getRealPath("/");
        
        reportEncaminhamento = new ReportEncaminhamento();
        
        String erro = reportEncaminhamento.gerar(caminho,listarRel());

        if (erro == null)
        {
            ByteArrayOutputStream bytes = reportEncaminhamento.getOutput();
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
    
    public List<Encaminhamento> listarRel()
    {
        System.out.println("Entrou");
        //pacPesq = pacPesq == null ? "" : pacPesq;
        //if(pacPesq.length() == 1)
        //{
            System.out.println("Entrou no método");
            listaEncaminhamento = encaminhamentoService.listarRel(pesquisaDataInicio);
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
        for(Encaminhamento e : listaEncaminhamento)
        {
            System.out.println("" + e.getMotivoEncaminhamento());
        }
        return listaEncaminhamento;
    }
    
    public Encaminhamento getEncaminhamento() {
        return encaminhamento;
    }

    public void setEncaminhamento(Encaminhamento encaminhamento) {
        this.encaminhamento = encaminhamento;
    }

    public Encaminhamento getEncaminhamentoSelecionado() {
        return encaminhamentoSelecionado;
    }

    public void setEncaminhamentoSelecionado(Encaminhamento encaminhamentoSelecionado) {
        this.encaminhamentoSelecionado = encaminhamentoSelecionado;
    }

    public List<Encaminhamento> getListaEncaminhamento() {
        return listaEncaminhamento;
    }

    public void setListaEncaminhamento(List<Encaminhamento> listaEncaminhamento) {
        this.listaEncaminhamento = listaEncaminhamento;
    }

    public String getEncPesq() {
        return encPesq;
    }

    public void setEncPesq(String encPesq) {
        this.encPesq = encPesq;
    }
    
}
