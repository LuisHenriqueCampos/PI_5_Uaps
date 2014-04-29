package managedbean;

import br.com.pi.entidade.Atribuicao;
import br.com.pi.service.IMedicoEnfermeiraService;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;
import br.com.pi.entidade.Medicoenfermeira;
import java.util.List;

/**
 *
 * @author petrovick
 */
@Named(value = "medicoEnfermeiraManagedBean")
@RequestScoped
public class MedicoEnfermeiraManagedBean
{
    @EJB
    IMedicoEnfermeiraService medicoEnfermeiraService;
    private Medicoenfermeira medicoenfermeira;
    private Medicoenfermeira medicoenfermeiraSelecionado;
    
    public MedicoEnfermeiraManagedBean()
    {
        medicoenfermeira = new Medicoenfermeira();
        medicoenfermeira.setIdAtribuicao(new Atribuicao());
    }
    
    public List<Medicoenfermeira> listar()
    {
        return medicoEnfermeiraService.listar();
    }

    public IMedicoEnfermeiraService getMedicoEnfermeiraService() {
        return medicoEnfermeiraService;
    }

    public void setMedicoEnfermeiraService(IMedicoEnfermeiraService medicoEnfermeiraService) {
        this.medicoEnfermeiraService = medicoEnfermeiraService;
    }

    public Medicoenfermeira getMedicoenfermeira() {
        return medicoenfermeira;
    }

    public void setMedicoenfermeira(Medicoenfermeira medicoenfermeira) {
        this.medicoenfermeira = medicoenfermeira;
    }
    
    
}
