package br.com.pi.service;

import br.com.pi.entidade.Postosaude;
import java.util.List;
/**
 *
 * @author petrovick
 */
public interface IPostosaudeService  extends ICrudGeneric<Postosaude>
{

    public String salvar(Postosaude entity);
    public String excluir(Postosaude Idobj);    
    public Postosaude obter(Integer IdObj);
    public List<Postosaude> listar();
}
