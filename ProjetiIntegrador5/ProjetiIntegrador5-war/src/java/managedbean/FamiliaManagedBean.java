package managedbean;

import br.com.pi.entidade.Endereco;
import br.com.pi.service.IFamiliaService;
import javax.ejb.EJB;
import javax.inject.Named;
import br.com.pi.entidade.Familia;
import java.util.List;
import javax.enterprise.context.RequestScoped;
/**
 *
 * @author petrovick
 */
@Named(value = "familiaManagedBean")
@RequestScoped
public class FamiliaManagedBean
{
    @EJB
    IFamiliaService familiaService;
    private Familia familia;
    private Familia familiaSelecionada;
    
    public FamiliaManagedBean()
    {
        familia = new Familia();
        familia.setIdEndereco(new Endereco());
    }
    
    public void salvar()
    {
        System.out.println("Entrou no salvar()");
        familiaService.salvar(familia);
    }
    
    public void excluir()
    {
        familiaService.excluir(familiaSelecionada);
    }
    
    public void novo()
    {
        familia = new Familia();
        familia.setIdEndereco(new Endereco());
    }
    
    public void editar()
    {
        System.out.println("Id:" + familiaSelecionada.getIdFamilia() + "Rua:"+ familiaSelecionada.getComplemento());
        familia = familiaSelecionada;
    }
    
    public List<Familia> listar()
    {
        return familiaService.listar();
    }

    public Familia getFamilia() {
        return familia;
    }

    public void setFamilia(Familia familia) {
        this.familia = familia;
    }

    public Familia getFamiliaSelecionada() {
        return familiaSelecionada;
    }

    public void setFamiliaSelecionada(Familia familiaSelecionada) {
        this.familiaSelecionada = familiaSelecionada;
    }
}
