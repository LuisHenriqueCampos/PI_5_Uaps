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
        return enderecoService.listar();
    }
    
    public void salvar()
    {
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
