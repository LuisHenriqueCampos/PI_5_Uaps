/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package managedbean;

import br.com.pi.entidade.Agentesaude;
import br.com.pi.entidade.Pessoaa;
import br.com.pi.service.IAgenteSaude;
import java.util.List;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;
import util.MenssagemUtil;

/**
 *
 * @author Luis
 */
@Named(value = "agentesaudeManagedBean")
@RequestScoped
public class AgenteSaudeManagedBean {
    
    @EJB
    IAgenteSaude agenteSaudeService;
    
    private Agentesaude agentesaude;
    private Agentesaude agentesaudeSelecionado;

    public AgenteSaudeManagedBean() {
        agentesaude = new Agentesaude();
        agentesaude.setPessoa(new Pessoaa());
    }
    
    public List<Agentesaude> todos(){
        return agenteSaudeService.listar();
    }
    
    public void salvar(){
        String erro = agenteSaudeService.salvar(agentesaude);
        if(erro==null){
            MenssagemUtil.addMensagemInfo("Agente de Saúde salvo com sucesso!");
            agentesaude = new Agentesaude();
        }else{
            MenssagemUtil.addMensagemError(erro);
        }
    }
    
    public void excluir(){
        String erro = agenteSaudeService.excluir(agentesaudeSelecionado);
        if(erro==null){
            MenssagemUtil.addMensagemInfo("Agente de Saúde excluído com sucesso!");
        }else{
            MenssagemUtil.addMensagemError(erro);
        }
    }
    
    public void novo(){
        this.agentesaude = new Agentesaude();
    }
    
    public void editar(){
        agentesaude = agentesaudeSelecionado;
    }

    public Agentesaude getAgentesaude() {
        return agentesaude;
    }

    public void setAgentesaude(Agentesaude agentesaude) {
        this.agentesaude = agentesaude;
    }

    public Agentesaude getAgentesaudeSelecionado() {
        return agentesaudeSelecionado;
    }

    public void setAgentesaudeSelecionado(Agentesaude agentesaudeSelecionado) {
        this.agentesaudeSelecionado = agentesaudeSelecionado;
    }    
    
}
