package managedbean;

import br.com.pi.entidade.Area;
import br.com.pi.service.IEnderecoService;
import br.com.pi.entidade.Endereco;
import br.com.pi.entidade.Microarea;
import br.com.pi.entidade.Postosaude;
import br.com.pi.report.ReportEndereco;
import br.com.pi.service.IAreaService;
import br.com.pi.service.IMicroareaService;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import javax.servlet.http.HttpServletResponse;
import util.MenssagemUtil;

/**
 *
 * @author petrovick
 */
@Named(value = "enderecoManagedBean")
@ViewScoped
public class EnderecoManagedBean
{
    @EJB
    IEnderecoService enderecoService;
    @EJB
    IAreaService areaService;
    @EJB
    IMicroareaService microareaService;
    
    private Endereco endereco;
    private String area;
    private String microArea;
    private Endereco enderecoSelecionado;
    
    private ReportEndereco reportEndereco;
    private List<Endereco> listaEndereco;
    private String endPesq;
    
    private Postosaude postosaude;
    private List<Area> areas;
    
    private Area areaarea;
    private List<Microarea> microareas;
    
    
    public EnderecoManagedBean()
    {
        endereco = new Endereco();
        reportEndereco = new ReportEndereco();

    }
    
    public List<Endereco> todos()
    {
        List<Endereco> l = enderecoService.listar();
        for(Endereco ll : l)
            System.out.println("ID: " + ll.getIdEndereco() + "Rua:" + ll.getRua());
        return l;
    }
    
    public void excluir()
    {
        String erro = enderecoService.excluir(enderecoSelecionado);
        if(erro==null){
            MenssagemUtil.addMensagemInfo("Endereço excluído com sucesso!");
        }else{
            MenssagemUtil.addMensagemError(erro);
        }
    }
    
    public void salvar()
    {
        String erro = enderecoService.salvar(endereco);
        if(erro==null){
            MenssagemUtil.addMensagemInfo("Endereço salvo com sucesso!");
            endereco = new Endereco();
        }else{
            MenssagemUtil.addMensagemError(erro);
        }
    }
    
    public void novo()
    {
        this.endereco = new Endereco();
    }
    
    public void listarPorPosto(){
        if(postosaude != null){
            areas = areaService.listarPorPosto(postosaude);
        }
    }
    
    public void listarPorArea(){
        if(area != null){
            microareas = microareaService.listarPorArea(areaarea);
        }
    }
    
    public void editar()
    {
        System.out.println("Endereco Id:" + enderecoSelecionado.getIdEndereco());
        System.out.println("Endereco Rua:" + enderecoSelecionado.getRua());
        
        this.endereco = enderecoSelecionado;
    }
    
    public List<Endereco> listarRel()
    {
        listaEndereco = enderecoService.listarRel(endPesq);
            
        for(Endereco e : listaEndereco)
        {
            System.out.println("" + e.getRua());
        }
        return listaEndereco;
    }
    
    public void gerarRelatorioFiltrado() throws IOException
    {
        String caminho = FacesContext.getCurrentInstance().getExternalContext().getRealPath("/");
        
        reportEndereco = new ReportEndereco();
        
        String erro = reportEndereco.gerar(caminho,listarRel());

        if (erro == null)
        {
            ByteArrayOutputStream bytes = reportEndereco.getOutput();
            HttpServletResponse res = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
            res.setContentType("application/pdf");
            res.setHeader("Content-disposition", "inline;filename=relatorio.pdf");
            res.getOutputStream().write(bytes.toByteArray());
            res.getCharacterEncoding();
            FacesContext.getCurrentInstance().responseComplete();

            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Relatório gerado com sucesso!", null));
        }
        
        else
        {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, erro, null));
        }
    }   
    
    public Endereco getEndereco() {
        return endereco;
    }

    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getMicroArea() {
        return microArea;
    }

    public void setMicroArea(String microArea) {
        this.microArea = microArea;
    }

    public Endereco getEnderecoSelecionado() {
        return enderecoSelecionado;
    }

    public void setEnderecoSelecionado(Endereco enderecoSelecionado) {
        this.enderecoSelecionado = enderecoSelecionado;
    }
    

    public List<Endereco> getListaEndereco() {
        return listaEndereco;
    }

    public void setListaEndereco(List<Endereco> listaEndereco) {
        this.listaEndereco = listaEndereco;
    }

    public String getEndPesq() {
        return endPesq;
    }

    public void setEndPesq(String endPesq) {
        this.endPesq = endPesq;
    }

    public Postosaude getPostosaude() {
        return postosaude;
    }

    public void setPostosaude(Postosaude postosaude) {
        this.postosaude = postosaude;
    }

    public List<Area> getAreas() {
        return areas;
    }

    public void setAreas(List<Area> areas) {
        this.areas = areas;
    }

    public Area getAreaarea() {
        return areaarea;
    }

    public void setAreaarea(Area areaarea) {
        this.areaarea = areaarea;
    }

    public List<Microarea> getMicroareas() {
        return microareas;
    }

    public void setMicroareas(List<Microarea> microareas) {
        this.microareas = microareas;
    }
    
    
}
