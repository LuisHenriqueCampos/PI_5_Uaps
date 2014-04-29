package managedbean;

import br.com.pi.service.ISexoService;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;
import br.com.pi.entidade.Sexo;
import java.util.List;
/**
 *
 * @author petrovick
 */
@Named(value = "sexoManagedBean")
@RequestScoped
public class SexoManagedBean
{
    @EJB
    ISexoService sexoService;
    private Sexo sexo;
    
    
    public SexoManagedBean()
    {
        sexo = new Sexo();
    }
    
    public List<Sexo> listar()
    {
        return sexoService.listar();
    }
}
