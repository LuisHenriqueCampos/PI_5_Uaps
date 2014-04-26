package converter;

import br.com.pi.entidade.Microarea;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

/**
 *
 * @author petrovick
 */
@FacesConverter(value = "microareaConverter", forClass = Microarea.class)
public class microareaConverter implements Converter
{

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        if(value == null || value.isEmpty())
            return null;
        try
        {
            Integer id = Integer.parseInt(value);
            Microarea micro = new Microarea();
            micro.setIdMicroArea(id);
            return micro;
        }catch(NumberFormatException ex)
        {
            return null;
        }
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        if(value == null || value.getClass() != Microarea.class)
            return null;
        Microarea micro = (Microarea) value;
        return micro.getIdMicroArea().toString();
    }
    
}
