

package br.com.pi.managedbean;

import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;
import br.com.pi.service.*;
import br.com.pi.entidade.*;
import java.util.List;
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
    
    public BairroManagedBean()
    {
        bairro = new Bairro();
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
