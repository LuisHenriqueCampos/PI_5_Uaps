package br.com.pi.managedbean;

import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;
import br.com.pi.entidade.*;
import br.com.pi.service.*;

/**
 *
 * @author petrovick
 */
@Named(value = "enderecoCadastroManagedBean")
@RequestScoped
public class EnderecoCadastroManagedBean
{
    @EJB
    IEnderecoService enderecoService;
    private Endereco endereco;
    private String area;
    private String microArea;
    private Endereco enderecoSelecionado;
    
    public EnderecoCadastroManagedBean()
    {
        endereco = new Endereco();
    }
    
    public void salvar()
    {
        enderecoService.salvar(endereco);
    }
    
    public void editar()
    {
        this.endereco = enderecoSelecionado;
    }
    
    public void excluir()
    {
        enderecoService.excluir(endereco);
    }
    
    public void novo()
    {
        endereco = new Endereco();
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
    
    
}
