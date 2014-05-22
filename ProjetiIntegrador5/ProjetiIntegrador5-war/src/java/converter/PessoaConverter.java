package converter;

import br.com.pi.entidade.Pessoaa;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

/**
 *
 * @author petrovick
 */
@FacesConverter(value = "pessoaConverter", forClass = Pessoaa.class)
public class PessoaConverter implements Converter
{

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value)
    {
        if(value == null || value.isEmpty())
            return null;
        
        try
        {
            Integer id = Integer.parseInt(value);
            Pessoaa end = new Pessoaa();
            end.setIdPessoa(id);
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
        if(value == null || value.getClass() != Pessoaa.class)
            return null;
        Pessoaa end = (Pessoaa) value;
        return end.getIdPessoa().toString();
    } 
}