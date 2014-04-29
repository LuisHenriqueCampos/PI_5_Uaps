/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package managedbean;

import br.com.pi.entidade.Atribuicao;
import br.com.pi.service.IAtribuicaoService;
import java.util.List;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;
import util.MenssagemUtil;

/**
 *
 * @author Luis
 */
@Named(value = "atribuicaoManagedBean")
@RequestScoped
public class AtribuicaoManagedBean {
    
    @EJB
    private IAtribuicaoService atribuicaoService;
    
    private Atribuicao atribuicao;
    private Atribuicao atribuicaoSelecionado;
    
    public AtribuicaoManagedBean(){
        atribuicao = new Atribuicao();
    }
    
    public List<Atribuicao> todos(){
        return atribuicaoService.listar();
    }
    
    public void novo(){
        this.atribuicao = new Atribuicao();
    }
    
    public void salvar(){
        String erro = atribuicaoService.salvar(atribuicao);
        if(erro == null){
            MenssagemUtil.addMensagemInfo("Atribuicao salva com sucesso!");
            novo();
        }else{
            MenssagemUtil.addMensagemError(erro);
        }
    }
    
    public void excluir(){
        String erro = atribuicaoService.excluir(atribuicaoSelecionado);
        if(erro == null){
            MenssagemUtil.addMensagemInfo("Atribuição excluida com sucesso!");
        }else{
            MenssagemUtil.addMensagemError(erro);
        }
    }
    
    public void atualizar(){
        atribuicao = atribuicaoSelecionado;
    }

    public Atribuicao getAtribuicao() {
        return atribuicao;
    }

    public void setAtribuicao(Atribuicao atribuicao) {
        this.atribuicao = atribuicao;
    }

    public Atribuicao getAtribuicaoSelecionado() {
        return atribuicaoSelecionado;
    }

    public void setAtribuicaoSelecionado(Atribuicao atribuicaoSelecionado) {
        this.atribuicaoSelecionado = atribuicaoSelecionado;
    }
    
}
