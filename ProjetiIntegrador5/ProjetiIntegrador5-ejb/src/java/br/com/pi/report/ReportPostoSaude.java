package br.com.pi.report;

import br.com.pi.serviceimple.PostosaudeService;
import br.com.pi.entidade.Postosaude;
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
import javax.ejb.EJB;

public class ReportPostoSaude {

    private ByteArrayOutputStream output;
    
    public String gerar(String caminho, List<Postosaude> postos)
    {
        String erro = null;

        try {
            output = new ByteArrayOutputStream();

            Document document = new Document(PageSize.A4.rotate());

            PdfWriter writer = PdfWriter.getInstance(document, output);

            PdfPageHelper helper = new PdfPageHelper(caminho);
            helper.setTitle("Relatório Quadro de Professores/ Disciplinas");

            writer.setPageEvent(helper);
            document.open();

            gerarRelatorio(postos, document);
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

    private void gerarRelatorio(List<Postosaude> postos, Document document) throws Exception {

        Font fontTexto = FontFactory.getFont("Arial", 12);
        Font fontTitulo = FontFactory.getFont("Arial", 12, Font.BOLD);

        String[] cabecalhos = {"Nome"};

        PdfPTable table = new PdfPTable(cabecalhos.length);
        table.setWidthPercentage(100);
        table.setWidths(new float[]{15});
        table.setHeaderRows(1);

        for (String cabecalho : cabecalhos)
        {
            PdfPCell cell = new PdfPCell(new Phrase(cabecalho, fontTitulo));
            cell.setBackgroundColor(BaseColor.LIGHT_GRAY);
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(cell);
        }

        for (Postosaude posto : postos) {
            PdfPCell cellNomePosto = new PdfPCell(new Phrase(posto.getNomePosto(), fontTexto));

            table.addCell(cellNomePosto);
        }

        document.add(table);

        Paragraph paragraph = new Paragraph("Possui " + postos.size() + "postos cadastradas.", fontTitulo);

        document.add(paragraph);

    }

}

//    private String location = "G:\\reports\report.pdf";
//    
//    public void createPdf()
//    {
//        try
//        {
//            Document document = new Document();
//            PdfWriter.getInstance(document, new FileOutputStream(location));
//            document.open();
//            
//            customisePdf(document, location);
//            document.close();
//        }
//        
//        catch(Exception e)
//        {
//            e.printStackTrace();
//        }
//    }
//    
//    @Override
//    public void customisePdf(Document document, String filename)
//    {
//        try
//        {
//            adicionarListaPostoSaude(document);
//        }
//        
//        catch (IOException ex)
//        {
//            ex.printStackTrace();
//        }
//        
//        catch (DocumentException ex) {
//            ex.printStackTrace();
//        }
//    }
//    
//    private void adicionarListaPostoSaude(Document document) throws IOException, DocumentException
//    {
//        PostosaudeService service = new PostosaudeService();
//        
//        // Create a table with 2 columns
//        PdfPTable table = new PdfPTable(new float[]{1, 7});
//        // Mark the table as not complete
//        table.setWidthPercentage(100);
//        
//        List<Postosaude> postos = service.listar();
//        
//        PdfPCell cell;
//        
//        for (Postosaude posto : postos)
//        {
//            // add movie information
//            cell = new PdfPCell();
//            
//            Paragraph p = new Paragraph(posto.getNomePosto());
//            p.setAlignment(Element.ALIGN_CENTER);
//            p.setSpacingBefore(5);
//            p.setSpacingAfter(5);
//            
//            cell.addElement(p);
//            cell.setBorder(PdfPCell.NO_BORDER);
//            
//            table.addCell(cell);
//        }
//        
//        document.add(table);
//    }
//}
