package br.com.pi.service;

import br.com.pi.entidade.Area;
import br.com.pi.entidade.Postosaude;
import java.util.List;

/**
 *
 * @author petrovick
 */
public interface IAreaService extends ICrudGeneric<Area>
{
    public String salvar(Area entity);
    public String excluir(Area Idobj);    
    public Area obter(Integer IdObj);
    public List<Area> listar();
    List<Area> listarPorPosto(Postosaude postosaude);
}
