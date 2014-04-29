package converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import br.com.pi.entidade.EspecialidadeEncaminhamento;

/**
 *
 * @author petrovick
 */
@FacesConverter(value = "especialidadeConverter", forClass = EspecialidadeEncaminhamento.class)
public class especialidadeConverter implements Converter
{

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value)
    {
        if(value == null || value.isEmpty())
            return null;
        try
        {
            Integer id = Integer.parseInt(value);
            EspecialidadeEncaminhamento esp = new EspecialidadeEncaminhamento();
            esp.setIdEspecialidade(id);
            return esp;
        }
        catch(NumberFormatException ex)
        {
            return null;
        }
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value)
    {
        if(value == null || value.getClass() != EspecialidadeEncaminhamento.class)
            return null;
        EspecialidadeEncaminhamento esp = (EspecialidadeEncaminhamento) value;
        return esp.getIdEspecialidade().toString();
        
    }
    
}
