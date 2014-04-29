/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package converter;

import br.com.pi.entidade.Medicoenfermeira;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

/**
 *
 * @author petrovick
 */
@FacesConverter(value = "medicoenfermeiraConverter", forClass = Medicoenfermeira.class)
public class medicoEnfermeiraConverter implements Converter
{
    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value)
    {
        if(value == null || value.isEmpty())
            return null;
        
        try
        {
            Integer id = Integer.parseInt(value);
            Medicoenfermeira end = new Medicoenfermeira();
            end.setIdPessoaMedicoEnfermeira(id);
            return end;
        }
        catch(NumberFormatException ex)
        {
            return null;
        }
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value)
    {
        if(value == null || value.getClass() != Medicoenfermeira.class)
            return null;
        Medicoenfermeira end = (Medicoenfermeira) value;
        return end.getIdPessoaMedicoEnfermeira().toString();
    } 
}
