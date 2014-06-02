package br.com.pi.service;

import br.com.pi.entidade.Agentesaude;
import java.util.List;

/**
 *
 * @author Luis
 */
public interface IAgenteSaude extends ICrudGeneric<Agentesaude>{
    
    public String salvar(Agentesaude entity);
    public String excluir(Agentesaude Idobj);    
    public Agentesaude obter(Integer IdObj);
    public List<Agentesaude> listar();
}
