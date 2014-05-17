package br.com.pi.report;

import br.com.pi.entidade.Encaminhamento;
import br.com.pi.util.PdfPageHelper;
import java.io.ByteArrayOutputStream;
import java.util.List;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

/**
 *
 * @author petrovick
 */
public class ReportEncaminhamento
{
    private ByteArrayOutputStream output;
    
    public String gerar(String caminho, List<Encaminhamento> encaminhmentos)
    {
        String erro = null;
        try
        {
            output = new ByteArrayOutputStream();
            Document document = new Document(PageSize.A4.rotate());
            PdfWriter writer = PdfWriter.getInstance(document, output);
            PdfPageHelper helper = new PdfPageHelper(caminho);
            helper.setTitle("Relatório de Encaminhamentos:");
            writer.setPageEvent(helper);
            document.open();
            
            gerarRelatorio(encaminhmentos, document);
            document.close();
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
            return ex.getMessage();
        }
        return erro;
    }
    
    public ByteArrayOutputStream getOutput()
    {
        return output;
    }
    
    private void gerarRelatorio(List<Encaminhamento> encaminhamentos, Document document) throws Exception
    {
        Font fontTexto = FontFactory.getFont("Arial", 12);
        Font fontTitulo = FontFactory.getFont("Arial", 12, Font.BOLD);
        
        String[] cabecalhos = {"Código Encaminhamento","Motivo Encaminhamento","Data Encaminhamento","Especialidade Encaminhamento","Paciente","Medico/Enfermeira","Tipo Encaminhamento"};//,  "idEspecialidade"};//,  "idtipoEncaminhamento", "idPessoaMedicoEnfermeira"};
        PdfPTable table = new PdfPTable(cabecalhos.length);
        table.setWidthPercentage(100);
        //table.setWidths(new float[]{15});
        table.setHeaderRows(1);
        
        for(String cabecalho : cabecalhos)
        {
            PdfPCell cell = new PdfPCell(new Phrase(cabecalho, fontTitulo));
            cell.setBackgroundColor(BaseColor.LIGHT_GRAY);
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(cell);
        }
        
        Locale ptBr = new Locale("pt", "BR");
        
        for(Encaminhamento encaminhamento : encaminhamentos)
        {
            PdfPCell cellCodigoEncaminhamento = new PdfPCell(new Phrase(encaminhamento.getIdEncaminhamento().toString()));
            PdfPCell cellMotivoEncaminhamento = new PdfPCell(new Phrase(encaminhamento.getMotivoEncaminhamento(), fontTexto));
            PdfPCell cellDataEncaminhamento = new PdfPCell(new Phrase(DateFormat.getDateInstance(DateFormat.SHORT,ptBr).format(encaminhamento.getDataEncaminhamento()), fontTexto));
            PdfPCell cellEspecialidadencaminhamento = new PdfPCell(new Phrase(encaminhamento.getIdEspecialidade().getDescricao(), fontTexto));
            PdfPCell cellPacienteEncaminhamento = new PdfPCell(new Phrase(encaminhamento.getIdPessoaPaciente().getPessoa().getNome(), fontTexto));
            PdfPCell cellMedicoEnfermeiraEncaminhamento = new PdfPCell(new Phrase(encaminhamento.getIdPessoaMedicoEnfermeira().getPessoa().getNome(), fontTexto));
            PdfPCell cellTipoEncaminhamento = new PdfPCell(new Phrase(encaminhamento.getIdtipoEncaminhamento().getDescricao(), fontTexto));
            
            table.addCell(cellCodigoEncaminhamento);
            table.addCell(cellMotivoEncaminhamento);
            table.addCell(cellDataEncaminhamento);
            table.addCell(cellEspecialidadencaminhamento);
            table.addCell(cellPacienteEncaminhamento);
            table.addCell(cellMedicoEnfermeiraEncaminhamento);
            table.addCell(cellTipoEncaminhamento);
        }
        
        document.add(table);
        System.out.println("Adiciona tabela.");
        Paragraph paragraph = new Paragraph("Possui " + encaminhamentos.size() + " encaminhamentos cadastrados");
        document.add(paragraph);
        System.out.println("Adiciona paragrafo.");
    }
}
