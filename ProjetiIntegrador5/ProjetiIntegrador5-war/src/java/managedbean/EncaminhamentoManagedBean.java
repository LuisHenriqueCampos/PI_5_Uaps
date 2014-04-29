package managedbean;

import br.com.pi.entidade.Encaminhamento;
import br.com.pi.entidade.EspecialidadeEncaminhamento;
import br.com.pi.entidade.Medicoenfermeira;
import br.com.pi.entidade.Paciente;
import br.com.pi.entidade.Tipoencaminhamento;
import br.com.pi.service.IEncaminhamentoService;
import java.util.List;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

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
    
    public EncaminhamentoManagedBean()
    {
        novo();
    }
    
    public void salvar()
    {
        try
        {
            System.out.println("Salvar no controller");
            //encaminhamentoService.salvar(encaminhamento);
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
        encaminhamentoService.excluir(encaminhamento);
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
    
}
