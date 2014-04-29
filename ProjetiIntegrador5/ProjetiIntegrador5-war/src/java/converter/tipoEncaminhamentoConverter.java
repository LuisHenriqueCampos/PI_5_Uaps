/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import br.com.pi.entidade.Tipoencaminhamento;

/**
 *
 * @author petrovick
 */
@FacesConverter(value = "tipoEncaminhamentoConverter", forClass = Tipoencaminhamento.class)
public class tipoEncaminhamentoConverter implements Converter
{

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value)
    {
        if(value == null || value.isEmpty())
            return null;
        try
        {
            Short id = Short.parseShort(value);
            Tipoencaminhamento tp = new Tipoencaminhamento();
            tp.setIdtipoEncaminhamento(id);
            return tp;
        }
        catch(NumberFormatException ex)
        {
            return null;
        }
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value)
    {
        if(value == null || value.getClass() != Tipoencaminhamento.class)
            return null;
        Tipoencaminhamento tp = (Tipoencaminhamento) value;
        return tp.getIdtipoEncaminhamento().toString();
    }
}