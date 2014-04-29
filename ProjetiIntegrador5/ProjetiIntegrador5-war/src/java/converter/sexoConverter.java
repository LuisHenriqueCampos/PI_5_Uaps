package converter;

import br.com.pi.entidade.Sexo;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

/**
 *
 * @author petrovick
 */
@FacesConverter(value = "sexoConverter", forClass = Sexo.class)
public class sexoConverter implements Converter
{

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value)
    {
        if(value == null || value.isEmpty())
            return null;
        
        try
        {
            Short id = Short.parseShort(value);
            Sexo s = new Sexo();
            s.setIdSexo(id);
            return s;
        }
        catch(NumberFormatException ex)
        {
            return null;
        }
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value)
    {
        if(value == null || value.getClass() != Sexo.class)
            return null;
        Sexo s = (Sexo) value;
        return s.getIdSexo().toString();
    } 
}
