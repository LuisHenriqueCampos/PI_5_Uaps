package converter;

import br.com.pi.entidade.Endereco;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

/**
 *
 * @author petrovick
 */
@FacesConverter(value = "enderecoConverter", forClass = Endereco.class)
public class enderecoConverter implements Converter
{

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value)
    {
        if(value == null || value.isEmpty())
            return null;
        
        try
        {
            Integer id = Integer.parseInt(value);
            Endereco end = new Endereco();
            end.setIdEndereco(id);
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
        Endereco end = (Endereco) value;
        return end.getIdEndereco().toString();
    } 
}
