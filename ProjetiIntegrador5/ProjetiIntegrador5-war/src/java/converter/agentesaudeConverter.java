/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package converter;

import br.com.pi.entidade.Agentesaude;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

/**
 *
 * @author Luis
 */
@FacesConverter(value = "agentesaudeConverter", forClass = Agentesaude.class)
public class agentesaudeConverter implements Converter
{

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value)
    {
        if(value == null || value.isEmpty())
            return null;
        try
        {
            Integer id = Integer.parseInt(value);
            Agentesaude ag = new Agentesaude();
            ag.setIdPessoaAgenteSaude(id);
            return ag;
        }
        catch(NumberFormatException ex)
        {
            return null;
        }
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value)
    {
        if(value == null || value.getClass() != Agentesaude.class)
            return null;
        Agentesaude pac = (Agentesaude) value;
        return pac.getPessoa().getIdPessoa().toString();
        
    }
}


