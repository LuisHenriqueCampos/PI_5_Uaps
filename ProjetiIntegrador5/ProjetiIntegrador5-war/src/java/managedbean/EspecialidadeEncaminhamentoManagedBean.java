/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package managedbean;

import br.com.pi.service.IEspecialidadeEncaminhamentoService;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;
import br.com.pi.entidade.EspecialidadeEncaminhamento;
import java.util.List;

/**
 *
 * @author petrovick
 */
@Named(value = "espcialidadeEncaminhamentoManagedBean")
@RequestScoped
public class EspecialidadeEncaminhamentoManagedBean
{
    @EJB
    IEspecialidadeEncaminhamentoService especialidadeService;
    private EspecialidadeEncaminhamento especialidade;
    private EspecialidadeEncaminhamento especialidadeSelecionado;
    
    public EspecialidadeEncaminhamentoManagedBean()
    {
        especialidade = new EspecialidadeEncaminhamento();
    }
    
    public List<EspecialidadeEncaminhamento> listar()
    {
        return especialidadeService.listar();
    }
    
}
