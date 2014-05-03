package managedbean;

import br.com.pi.entidade.Atribuicao;
import br.com.pi.service.IMedicoEnfermeiraService;
import br.com.pi.entidade.Medicoenfermeira;
import br.com.pi.entidade.Pessoaa;
import java.util.List;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

/**
 *
 * @author Luis
 */
@Named(value = "medicoEnfermeiraManagedBean")
@RequestScoped
public class MedicoEnfermeiraManagedBean
{
    @EJB
    IMedicoEnfermeiraService medicoEnfermeiraService;
    private Medicoenfermeira medicoEnfermeira;
    private List<Medicoenfermeira> medicoEnfermeiras;
    private Medicoenfermeira medicoenfermeiraSelecionado;
    
    public MedicoEnfermeiraManagedBean()
    {
        medicoEnfermeira = new Medicoenfermeira();
        medicoEnfermeira.setIdAtribuicao(new Atribuicao());
        medicoEnfermeira.setPessoa(new Pessoaa());
    }
    
    public void novo()
    {
        medicoEnfermeira = new Medicoenfermeira();
    }
    
    public void editar()
    {
        System.out.println("\n\n\n EditarSelecionado:" + medicoenfermeiraSelecionado);
        medicoEnfermeira = medicoenfermeiraSelecionado;
    }
    
    public void excluir()
    {
        System.out.println("\n\n\n Excluir Selecionado:" + medicoenfermeiraSelecionado);
        medicoEnfermeiraService.excluir(medicoenfermeiraSelecionado);
    }
    
    public void salvar()
    {
        System.out.println("" + medicoEnfermeira.getAssinatura());
        System.out.println("" + medicoEnfermeira.getIdAtribuicao().getDescricao());
        System.out.println("" + medicoEnfermeira.getPessoa().getNome());
        System.out.println("" + medicoEnfermeira.getIdPessoaMedicoEnfermeira());
        medicoEnfermeiraService.salvar(medicoEnfermeira);
    }
    
    public List<Medicoenfermeira> listar()
    {
        return medicoEnfermeiraService.listar();
    }

    public Medicoenfermeira getMedicoEnfermeira() {
        return medicoEnfermeira;
    }

    public void setMedicoEnfermeira(Medicoenfermeira medicoEnfermeira) {
        this.medicoEnfermeira = medicoEnfermeira;
    }

    public List<Medicoenfermeira> getMedicoEnfermeiras() {
        return medicoEnfermeiras;
    }

    public void setMedicoEnfermeiras(List<Medicoenfermeira> medicoEnfermeiras) {
        this.medicoEnfermeiras = medicoEnfermeiras;
    }

    public Medicoenfermeira getMedicoenfermeiraSelecionado() {
        return medicoenfermeiraSelecionado;
    }

    public void setMedicoenfermeiraSelecionado(Medicoenfermeira medicoenfermeiraSelecionado) {
        this.medicoenfermeiraSelecionado = medicoenfermeiraSelecionado;
    }
    
    
    
    
}
