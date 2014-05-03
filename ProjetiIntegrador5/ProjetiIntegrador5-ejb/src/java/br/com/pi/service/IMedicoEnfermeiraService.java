package br.com.pi.service;

import br.com.pi.entidade.Medicoenfermeira;
import java.util.List;

/**
 *
 * @author petrovick
 */
public interface IMedicoEnfermeiraService  extends ICrudGeneric<Medicoenfermeira>
{
    public String salvar(Medicoenfermeira entity);
    public String excluir(Medicoenfermeira Idobj);    
    public Medicoenfermeira obter(Integer IdObj);
    public List<Medicoenfermeira> listar();
    List<Medicoenfermeira> listarPorAtribuicao(String atribuicao);
}
