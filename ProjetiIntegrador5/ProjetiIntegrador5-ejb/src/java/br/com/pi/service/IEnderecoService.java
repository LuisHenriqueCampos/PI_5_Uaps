package br.com.pi.service;

import br.com.pi.entidade.Endereco;
import java.util.List;

/**
 *
 * @author petrovick
 */
public interface IEnderecoService extends ICrudGeneric<Endereco>
{
    public String salvar(Endereco entity);
    public String excluir(Endereco Idobj);    
    public Endereco obter(Integer IdObj);
    public List<Endereco> listar();
    
}
