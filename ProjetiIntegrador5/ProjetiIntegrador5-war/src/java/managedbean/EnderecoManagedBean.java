package managedbean;

import br.com.pi.entidade.Area;
import br.com.pi.entidade.Bairro;
import br.com.pi.service.IEnderecoService;
import br.com.pi.entidade.Endereco;
import br.com.pi.entidade.Microarea;
import br.com.pi.report.ReportEndereco;
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
@Named(value = "enderecoManagedBean")
@RequestScoped
public class EnderecoManagedBean
{
    @EJB
    IEnderecoService enderecoService;
    private Endereco endereco;
    private String area;
    private String microArea;
    private Endereco enderecoSelecionado;
    
    private ReportEndereco reportEndereco;
    private List<Endereco> listaEndereco;
    private String endPesq;
    
    public EnderecoManagedBean()
    {
        endereco = new Endereco();
        reportEndereco = new ReportEndereco();
        Microarea ma = new Microarea();
        ma.setIdArea(new Area());
        endereco.setIdMicroArea(ma);
        endereco.setIdBairro(new Bairro());
    }
    
    public List<Endereco> todos()
    {
        List<Endereco> l = enderecoService.listar();
        for(Endereco ll : l)
            System.out.println("ID: " + ll.getIdEndereco() + "Rua:" + ll.getRua());
        return l;
    }
    
    public void excluir()
    {
        enderecoService.excluir(enderecoSelecionado);
    }
    
    public void salvar()
    {
        System.out.println("Endereco Id:" + endereco.getIdEndereco());
        System.out.println("Endereco Rua:" + endereco.getRua());
        
        enderecoService.salvar(endereco);
    }
    
    public void novo()
    {
        this.endereco = new Endereco();
        Microarea ma = new Microarea();
        ma.setIdArea(new Area());
        endereco.setIdMicroArea(ma);
        endereco.setIdBairro(new Bairro());
    }
    
    public void editar()
    {
        System.out.println("Endereco Id:" + enderecoSelecionado.getIdEndereco());
        System.out.println("Endereco Rua:" + enderecoSelecionado.getRua());
        
        this.endereco = enderecoSelecionado;
    }
    
    public List<Endereco> listarRel()
    {
        System.out.println("Entrou");
        //pacPesq = pacPesq == null ? "" : pacPesq;
        //if(pacPesq.length() == 1)
        //{
            System.out.println("Entrou no método");
            listaEndereco = enderecoService.listarRel(endPesq);
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
        for(Endereco e : listaEndereco)
        {
            System.out.println("" + e.getRua());
        }
        return listaEndereco;
    }
    
    public void gerarRelatorioFiltrado() throws IOException
    {
        String caminho = FacesContext.getCurrentInstance().getExternalContext().getRealPath("/");
        
        reportEndereco = new ReportEndereco();
        
        String erro = reportEndereco.gerar(caminho,listarRel());

        if (erro == null)
        {
            ByteArrayOutputStream bytes = reportEndereco.getOutput();
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
    
    
    
    
    
    public Endereco getEndereco() {
        return endereco;
    }

    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getMicroArea() {
        return microArea;
    }

    public void setMicroArea(String microArea) {
        this.microArea = microArea;
    }

    public Endereco getEnderecoSelecionado() {
        return enderecoSelecionado;
    }

    public void setEnderecoSelecionado(Endereco enderecoSelecionado) {
        this.enderecoSelecionado = enderecoSelecionado;
    }
    

    public List<Endereco> getListaEndereco() {
        return listaEndereco;
    }

    public void setListaEndereco(List<Endereco> listaEndereco) {
        this.listaEndereco = listaEndereco;
    }

    public String getEndPesq() {
        return endPesq;
    }

    public void setEndPesq(String endPesq) {
        this.endPesq = endPesq;
    }
    
    
}
