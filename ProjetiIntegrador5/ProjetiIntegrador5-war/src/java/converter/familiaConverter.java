package converter;

import br.com.pi.entidade.Familia;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

/**
 *
 * @author Luis
 */
@FacesConverter(value = "familiaConverter",forClass = Familia.class)
public class familiaConverter implements Converter{
        
    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value)
    {
        if(value == null || value.isEmpty())
            return null;
        try
        {
            Integer id = Integer.parseInt(value);
            Familia familia = new Familia();
            familia.setIdFamilia(id);
            return familia;
        }
        catch(NumberFormatException ex)
        {
            return null;
        }
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value)
    {
        if(value == null || value.getClass() != Familia.class)
            return null;
        Familia fa = (Familia) value;
        return fa.getIdFamilia().toString();
        
    }
}