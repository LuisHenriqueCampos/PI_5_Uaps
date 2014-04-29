/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package converter;

import br.com.pi.entidade.Atribuicao;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;


/**
 *
 * @author Luis
 */
@FacesConverter(value = "atribuicaoConverter", forClass = Atribuicao.class)
public class AtribuicaoConverter implements Converter{

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        
        if(value == null || value.isEmpty())
            return null;
        try
        {
            Short id = Short.parseShort(value);
            Atribuicao atribuicao = new Atribuicao();
            atribuicao.setIdAtribuicao(id);
            return atribuicao;
        }
        catch(NumberFormatException ex)
        {
            ex.printStackTrace();
            return null;
        }
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        
        if(value == null || value.getClass() != Atribuicao.class)
            return null;
        Atribuicao a = (Atribuicao) value;
        return a.getIdAtribuicao().toString();
    }    
    
}
