package br.com.pi.service;

import br.com.pi.entidade.Encaminhamento;
import java.util.List;

/**
 *
 * @author petrovick
 */
public interface IEncaminhamentoService extends ICrudGeneric<Encaminhamento>
{
    public String salvar(Encaminhamento entity);
    public String excluir(Encaminhamento Idobj);    
    public Encaminhamento obter(Integer IdObj);
    public List<Encaminhamento> listar();
}