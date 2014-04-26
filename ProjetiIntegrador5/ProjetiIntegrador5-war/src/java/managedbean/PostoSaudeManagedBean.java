package br.com.pi.managedbean;

import br.com.pi.service.IPostosaudeService;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;
import br.com.pi.entidade.Postosaude;
import java.util.List;

/**
 *
 * @author petrovick
 */
@Named(value = "postoSaudeManagedBean")
@RequestScoped
public class PostoSaudeManagedBean
{
    @EJB
    IPostosaudeService postoService;
    private Postosaude postoSaude;
    private Postosaude postosaudeSelecionado;
    
    public PostoSaudeManagedBean()
    {
        postoSaude = new Postosaude();
    }
    
    public void editar()
    {
        postoSaude = postosaudeSelecionado;
    }
    
    public void excluir()
    {
        postoService.excluir(postoSaude);
    }
    
    public void salvar()
    {
        postoService.salvar(postoSaude);
    }
    
    public void novo()
    {
        this.postoSaude = new Postosaude();
    }
    
    public List<Postosaude> todos()
    {
        List<Postosaude> postos = postoService.listar();
        for(Postosaude p : postos)
            System.out.println("" + p.getNomePosto());
        return postoService.listar();
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
    
    
    
}
