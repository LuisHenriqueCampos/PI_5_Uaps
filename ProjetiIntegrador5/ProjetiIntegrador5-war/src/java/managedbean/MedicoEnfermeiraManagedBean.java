package managedbean;

import br.com.pi.entidade.Atribuicao;
import br.com.pi.entidade.Medicoenfermeira;
import br.com.pi.service.IMedicoEnfermeiraService;
import java.io.IOException;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import util.MenssagemUtil;

/**
 *
 * @author petrovick
 */
@Named(value = "medicoEnfermeiraManagedBean")
@RequestScoped
public class MedicoEnfermeiraManagedBean {
    
    @EJB
    private IMedicoEnfermeiraService medicoEnfermeiraService;
    
    private Medicoenfermeira medicoenfermeira;
    private Medicoenfermeira medicoenfermeiraSelecionado;
    private List<Medicoenfermeira> medicoenfermeiras;
    private Atribuicao atribuicao;
    private String atribuicaoConsulta;
    
    public MedicoEnfermeiraManagedBean(){
        medicoenfermeira = new Medicoenfermeira();
    }
    
    @PostConstruct
    public void init(){
        String id = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("id");
        
        if(id != null){
            medicoenfermeira = medicoEnfermeiraService.obter(Integer.parseInt(id));
            atribuicao = medicoenfermeira.getIdAtribuicao();
        }
    }
    
    public void salvar(){
        
        System.out.println("nomenomenomenomenomenome#### :"+medicoenfermeira.getPessoa().getNome());
        
        String erro = medicoEnfermeiraService.salvar(medicoenfermeira);
        
        if(erro == null){
            MenssagemUtil.addMensagemInfo("Cadastro realizado com sucesso!");
            this.medicoenfermeira = new Medicoenfermeira();
        }else{
            MenssagemUtil.addMensagemError(erro);
        }
    }
    
    public void excluir(){
        String erro = medicoEnfermeiraService.excluir(medicoenfermeiraSelecionado);
        
        if(erro == null){
            MenssagemUtil.addMensagemInfo("Cadastro excluído com sucesso!");
            this.medicoenfermeiras.remove(medicoenfermeiraSelecionado);
        }else{
            MenssagemUtil.addMensagemError(erro);
        }
    }
    
    public void editar() throws IOException{
        FacesContext.getCurrentInstance().getExternalContext()
                .redirect("formulario.html?id="+medicoenfermeiraSelecionado.getPessoa().getIdPessoa());
    }
    
    public void pesquisar(){
        this.medicoenfermeiras = medicoEnfermeiraService.listarPorAtribuicao(atribuicaoConsulta);
    }

    public Medicoenfermeira getMedicoenfermeira() {
        return medicoenfermeira;
    }

    public void setMedicoenfermeira(Medicoenfermeira medicoenfermeira) {
        this.medicoenfermeira = medicoenfermeira;
    }

    public Medicoenfermeira getMedicoenfermeiraSelecionado() {
        return medicoenfermeiraSelecionado;
    }

    public void setMedicoenfermeiraSelecionado(Medicoenfermeira medicoenfermeiraSelecionado) {
        this.medicoenfermeiraSelecionado = medicoenfermeiraSelecionado;
    }

    public List<Medicoenfermeira> getMedicoenfermeiras() {
        return medicoenfermeiras;
    }

    public void setMedicoenfermeiras(List<Medicoenfermeira> medicoenfermeiras) {
        this.medicoenfermeiras = medicoenfermeiras;
    }

    public Atribuicao getAtribuicao() {
        return atribuicao;
    }

    public void setAtribuicao(Atribuicao atribuicao) {
        this.atribuicao = atribuicao;
    }

    public String getAtribuicaoConsulta() {
        return atribuicaoConsulta;
    }

    public void setAtribuicaoConsulta(String atribuicaoConsulta) {
        this.atribuicaoConsulta = atribuicaoConsulta;
    }
    
}
