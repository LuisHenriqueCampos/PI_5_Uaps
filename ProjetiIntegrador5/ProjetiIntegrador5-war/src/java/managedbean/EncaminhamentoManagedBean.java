package managedbean;

import br.com.pi.entidade.Encaminhamento;
import br.com.pi.entidade.EspecialidadeEncaminhamento;
import br.com.pi.entidade.Medicoenfermeira;
import br.com.pi.entidade.Tipoencaminhamento;
import br.com.pi.entidade.Paciente;
import br.com.pi.entidade.Pessoaa;
import br.com.pi.report.ReportEncaminhamento;
import br.com.pi.service.IEncaminhamentoService;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Date;
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
    private Date pesquisaDataInicio;
    private Date pesquisaDataFim;
    
    public EncaminhamentoManagedBean()
    {
        encaminhamento = new Encaminhamento();
        encaminhamento.setDataEncaminhamento(new Date());
        encaminhamento.setIdEspecialidade(new EspecialidadeEncaminhamento());
        encaminhamento.setIdPessoaMedicoEnfermeira(new Medicoenfermeira());
        Paciente p = new Paciente();
        p.setPessoa(new Pessoaa());
        encaminhamento.setIdPessoaPaciente(p);
        encaminhamento.setIdtipoEncaminhamento(new Tipoencaminhamento());
        
        reportEncaminhamento = new ReportEncaminhamento();
    }
    
    public void salvar()
    {
        System.out.println("Entrou no salvar.");
        System.out.println("" + encaminhamento);
        System.out.println(encaminhamento.getIdPessoaPaciente());
        System.out.println(encaminhamento.getIdPessoaPaciente().getIdPessoaPaciente());
        //System.out.println(encaminhamento.getIdPessoaPaciente().getPessoa().getNome());
        System.out.println(encaminhamento.getIdPessoaPaciente().getPessoa());
        System.out.println(encaminhamento.getMotivoEncaminhamento());
        
        String erro = encaminhamentoService.salvar(encaminhamento);
        if(erro == null){
            MenssagemUtil.addMensagemInfo("Encaminhamento Registrado com Sucesso!");
            encaminhamento = new Encaminhamento();
        }else{
            MenssagemUtil.addMensagemError(erro);
        }
    }
    
    public List<Encaminhamento> listar()
    {
        return encaminhamentoService.listar();
    }
    
    public void excluir()
    {
        String erro = encaminhamentoService.excluir(encaminhamentoSelecionado);
        if(erro == null){
            MenssagemUtil.addMensagemInfo("Encaminhamento Excluído com Sucesso!");
        }else{
            MenssagemUtil.addMensagemError(erro);
        }
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
        System.out.println("Entrou no método");
        System.out.println("Data Inicio:" + pesquisaDataInicio);
        System.out.println("Data Fim   :" + pesquisaDataFim);
        listaEncaminhamento = encaminhamentoService.listarRel(pesquisaDataInicio, pesquisaDataFim);
            
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

    public Date getPesquisaDataInicio() {
        return pesquisaDataInicio;
    }

    public void setPesquisaDataInicio(Date pesquisaDataInicio) {
        this.pesquisaDataInicio = pesquisaDataInicio;
    }

    public Date getPesquisaDataFim() {
        return pesquisaDataFim;
    }

    public void setPesquisaDataFim(Date pesquisaDataFim) {
        this.pesquisaDataFim = pesquisaDataFim;
    }
}
