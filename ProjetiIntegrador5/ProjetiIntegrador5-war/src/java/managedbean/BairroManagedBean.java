

package br.com.pi.managedbean;

import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;
import br.com.pi.service.*;
import br.com.pi.entidade.*;
import java.util.List;
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
    
    public BairroManagedBean()
    {
        bairro = new Bairro();
    }
    
    public void editar()
    {
        
    }
    
    public void novo()
    {
        
    }
    
    public void salvar()
    {
        
    }
    
    public List<Bairro> todos()
    {
        return bairroService.listar();
    }
    
    public void excluir()
    {
        
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
    
    
}
