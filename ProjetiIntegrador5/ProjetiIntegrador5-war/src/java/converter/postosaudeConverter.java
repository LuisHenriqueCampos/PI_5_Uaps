/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package converter;

import br.com.pi.entidade.Postosaude;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

/**
 *
 * @author petrovick
 */
@FacesConverter(value = "postosaudeConverter", forClass = Postosaude.class)
public class postosaudeConverter implements Converter
{

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value)
    {
        if(value == null || value.isEmpty())
            return null;
        try
        {
            Short id = Short.parseShort(value);
            Postosaude pac = new Postosaude();
            pac.setIdPostoSaude(id);
            return pac;
        }
        catch(NumberFormatException ex)
        {
            return null;
        }
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value)
    {
        if(value == null || value.getClass() != Postosaude.class)
            return null;
        Postosaude pac = (Postosaude) value;
        return pac.getIdPostoSaude().toString();
        
    }
}

