package managedbean;

import br.com.pi.entidade.Familia;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;
import br.com.pi.entidade.Paciente;
import br.com.pi.entidade.Pessoaa;
import br.com.pi.entidade.Sexo;
import br.com.pi.service.IPacienteService;
import java.util.List;
/**
 *
 * @author petrovick
 */
@Named(value = "pacienteManagedBean")
@RequestScoped
public class PacienteManagedBean
{
    @EJB
    private IPacienteService pacienteService;
    private Paciente paciente;
    private Paciente pacienteSelecionado;
    
    public PacienteManagedBean()
    {
        paciente = new Paciente();
        paciente.setPessoa(new Pessoaa());
        paciente.setIdSexo(new Sexo());
        paciente.setIdFamilia(new Familia());
    }
    
    public void salvar()
    {
        try
        {
            pacienteService.salvar(paciente);
        }
        catch(Exception ex)
        {
            throw ex;
        }
    }
    
    public List<Paciente> listar()
    {
        return pacienteService.listar();
    }
    
    public void excluir()
    {
        pacienteService.excluir(pacienteSelecionado);
    }
    
    public void editar()
    {
        this.paciente = pacienteSelecionado;
    }
    
    public void novo()
    {
        this.paciente = new Paciente();
    }

    public Paciente getPaciente() {
        return paciente;
    }

    public void setPaciente(Paciente paciente) {
        this.paciente = paciente;
    }

    public Paciente getPacienteSelecionado() {
        return pacienteSelecionado;
    }

    public void setPacienteSelecionado(Paciente pacienteSelecionado) {
        this.pacienteSelecionado = pacienteSelecionado;
    }
}
