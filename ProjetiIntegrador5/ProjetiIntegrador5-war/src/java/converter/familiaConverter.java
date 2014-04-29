package converter;

import br.com.pi.entidade.Familia;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
/**
 *
 * @author petrovick
 */
@FacesConverter(value = "familiaConverter", forClass = Familia.class)
public class familiaConverter implements Converter
{

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        System.out.println("CONVERTER getAsObject:" + value);
        if(value == null || value.isEmpty())
        {
            System.out.println("erro no getAsObject\n\n\n\n");
            return null;
        }
        try
        {
            Integer id = Integer.parseInt(value);
            Familia familia = new Familia();
            familia.setIdFamilia(id);
            return familia;
        }catch(NumberFormatException e)
        {
            return null;
        }
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        System.out.println("CONVERTER getAsString:");
        if(value == null || value.getClass() != Familia.class)
        {
            System.out.println("erro no getAsString\n\n\n\n");
            return null;
        }
        Familia familia = (Familia)value;
        return familia.getIdFamilia().toString();
    }
    
    
}
