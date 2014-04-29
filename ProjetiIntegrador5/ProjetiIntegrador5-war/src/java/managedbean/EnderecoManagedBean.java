package br.com.pi.managedbean;

import br.com.pi.entidade.Area;
import br.com.pi.entidade.Bairro;
import br.com.pi.service.IEnderecoService;
import br.com.pi.entidade.Endereco;
import br.com.pi.entidade.Microarea;
import java.util.List;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

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
    
    public EnderecoManagedBean()
    {
        endereco = new Endereco();
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
    
    
}
