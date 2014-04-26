package br.com.pi.service;

import br.com.pi.entidade.Microarea;
import java.util.List;

/**
 *
 * @author petrovick
 */
public interface IMicroareaService extends ICrudGeneric<Microarea>
{

    public String salvar(Microarea entity);
    public String excluir(Microarea Idobj);    
    public Microarea obter(Integer IdObj);
    public List<Microarea> listar();
    
}
