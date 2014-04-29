package br.com.pi.converter;

import br.com.pi.entidade.Area;
import br.com.pi.entidade.Endereco;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

/**
 *
 * @author petrovick
 */
@FacesConverter(value = "areaConverter", forClass = Endereco.class)
public class areaConverter implements Converter
{

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value)
    {
        if(value == null || value.isEmpty())
            return null;
        
        try
        {
            Short id = Short.parseShort(value);
            Area end = new Area();
            end.setIdArea(id);
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
        if(value == null || value.getClass() != Endereco.class)
            return null;
        Area end = (Area) value;
        return end.getIdArea().toString();
    } 
}
