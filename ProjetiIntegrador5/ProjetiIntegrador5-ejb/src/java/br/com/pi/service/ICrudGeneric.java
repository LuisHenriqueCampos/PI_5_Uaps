package br.com.pi.service;

import java.util.List;

/**
 *
 * @author petrovick
 */
public interface ICrudGeneric<T>
{
    public String salvar(T entity);
    public String excluir(T Idobj);    
    public T obter(Integer IdObj);
    public List<T> listar();
}
