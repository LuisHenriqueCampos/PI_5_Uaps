
package managedbean;

import br.com.pi.entidade.Pessoaa;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;
import br.com.pi.service.IPessoaService;
import java.util.List;
import javax.ejb.EJB;

/**
 *
 * @author petrovick
 */
@Named(value = "pessoaManagedBean")
@RequestScoped
public class PessoaManagedBean
{
    @EJB
    private IPessoaService pessoaService;
    
    public List<Pessoaa> listar()
    {
        return pessoaService.listar();
    }
    
    
}
