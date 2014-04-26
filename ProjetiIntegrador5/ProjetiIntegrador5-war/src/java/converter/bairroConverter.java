package converter;

import br.com.pi.entidade.Bairro;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
/**
 *
 * @author petrovick
 */
@FacesConverter(value = "bairroConverter", forClass = Bairro.class)
public class bairroConverter implements Converter
{

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value)
    {
        if(value == null || value.isEmpty())
            return null;
        try
        {
            Integer id = Integer.parseInt(value);
            Bairro b = new Bairro();
            b.setIdBairro(id);
            return b;
        }
        catch(NumberFormatException ex)
        {
            return null;
        }
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value)
    {
        if(value == null || value.getClass() != Bairro.class)
            return null;
        Bairro b = (Bairro) value;
        return b.getIdBairro().toString();
    }
    
}
