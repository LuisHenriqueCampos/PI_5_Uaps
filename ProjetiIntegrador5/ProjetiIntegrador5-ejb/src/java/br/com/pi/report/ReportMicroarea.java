package br.com.pi.report;

import br.com.pi.entidade.Microarea;
import br.com.pi.util.PdfPageHelper;
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
import java.io.ByteArrayOutputStream;
import java.util.List;

/**
 *
 * @author petrovick
 */
public class ReportMicroarea
{
    private ByteArrayOutputStream output;
    
    public String gerar(String caminho, List<Microarea> microareas)
    {
        String erro = null;

        try {
            output = new ByteArrayOutputStream();

            Document document = new Document(PageSize.A4.rotate());

            PdfWriter writer = PdfWriter.getInstance(document, output);

            PdfPageHelper helper = new PdfPageHelper(caminho);
            helper.setTitle("Relatório de Microarea");

            writer.setPageEvent(helper);
            document.open();

            gerarRelatorio(microareas, document);
            document.close();
        }
        
        catch (Exception ex) {
            ex.printStackTrace();
            return ex.getMessage();
        }

        return erro;
    }

    public ByteArrayOutputStream getOutput() {
        return output;
    }

    private void gerarRelatorio(List<Microarea> microareas, Document document) throws Exception {

        Font fontTexto = FontFactory.getFont("Arial", 12);
        Font fontTitulo = FontFactory.getFont("Arial", 12, Font.BOLD);

        String[] cabecalhos = {"Codigo","Descrição","Area"};
        
        PdfPTable table = new PdfPTable(cabecalhos.length);
        table.setWidthPercentage(100);
        //table.setWidths(new float[]{15});
        table.setHeaderRows(1);

        for (String cabecalho : cabecalhos)
        {
            PdfPCell cell = new PdfPCell(new Phrase(cabecalho, fontTitulo));
            cell.setBackgroundColor(BaseColor.LIGHT_GRAY);
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(cell);
        }

        for (Microarea microarea : microareas) {
            PdfPCell cellCodigoMicroarea = new PdfPCell(new Phrase(microarea.getIdMicroArea().toString(), fontTexto));
            PdfPCell cellDescricaoMicroarea = new PdfPCell(new Phrase(microarea.getDescricao(), fontTexto));
            PdfPCell cellAreaMicroarea = new PdfPCell(new Phrase(microarea.getIdArea().getDescricao(), fontTexto));
            
            table.addCell(cellCodigoMicroarea);
            table.addCell(cellDescricaoMicroarea);
            table.addCell(cellAreaMicroarea);
        }

        document.add(table);

        Paragraph paragraph = new Paragraph("Possui " + microareas.size() + " microareas  cadastradas.", fontTitulo);

        document.add(paragraph);

    }

}