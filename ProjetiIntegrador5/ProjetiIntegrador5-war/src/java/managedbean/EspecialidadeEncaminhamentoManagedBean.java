package managedbean;

import br.com.pi.service.IEspecialidadeEncaminhamentoService;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;
import br.com.pi.entidade.EspecialidadeEncaminhamento;
import java.util.List;
import util.MenssagemUtil;

/**
 *
 * @author petrovick
 */
@Named(value = "especialidadeEncaminhamentoManagedBean")
@RequestScoped
public class EspecialidadeEncaminhamentoManagedBean
{
    @EJB
    IEspecialidadeEncaminhamentoService especialidadeService;
    private EspecialidadeEncaminhamento especialidade;
    private EspecialidadeEncaminhamento especialidadeSelecionado;
    
    public EspecialidadeEncaminhamentoManagedBean()  {
        novo();
    }  
    public List<EspecialidadeEncaminhamento> listar() {
        return especialidadeService.listar();
    }
    public void salvar() {
        System.out.println("desc" + especialidade.getDescricao());
        System.out.println("Id" + especialidade.getIdEspecialidade());
        
        String erro = especialidadeService.salvar(especialidade);
        if(erro == null)
        {
            MenssagemUtil.addMensagemInfo("Salvo com sucesso");
            novo();
        }
        else
            MenssagemUtil.addMensagemError(erro);
                
    }
    public void editar() {
        especialidade = especialidadeSelecionado;
    }
    public void excluir() {
        String erro = especialidadeService.excluir(especialidadeSelecionado);
        if(erro == null)
            MenssagemUtil.addMensagemInfo("Salvo com sucesso");
        else
            MenssagemUtil.addMensagemError(erro);
    }
    public void novo() {
        especialidade = new EspecialidadeEncaminhamento();
    }

    public EspecialidadeEncaminhamento getEspecialidade() {
        return especialidade;
    }

    public void setEspecialidade(EspecialidadeEncaminhamento especialidade) {
        this.especialidade = especialidade;
    }

    public EspecialidadeEncaminhamento getEspecialidadeSelecionado() {
        return especialidadeSelecionado;
    }

    public void setEspecialidadeSelecionado(EspecialidadeEncaminhamento especialidadeSelecionado) {
        this.especialidadeSelecionado = especialidadeSelecionado;
    }
    
    
}
