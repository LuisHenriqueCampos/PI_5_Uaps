package br.com.pi.report;

import br.com.pi.entidade.Familia;
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
public class ReportFamilia {
    private ByteArrayOutputStream output;
    
    public String gerar(String caminho, List<Familia> familias)
    {
        String erro = null;

        try {
            output = new ByteArrayOutputStream();

            Document document = new Document(PageSize.A4.rotate());

            PdfWriter writer = PdfWriter.getInstance(document, output);

            PdfPageHelper helper = new PdfPageHelper(caminho);
            helper.setTitle("Relatório de Família");

            writer.setPageEvent(helper);
            document.open();

            gerarRelatorio(familias, document);
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

    private void gerarRelatorio(List<Familia> familias, Document document) throws Exception {

        Font fontTexto = FontFactory.getFont("Arial", 12);
        Font fontTitulo = FontFactory.getFont("Arial", 12, Font.BOLD);

        String[] cabecalhos = {"Codigo Familia","Descriçao","Complemento","Endereço"};

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

        for (Familia familia : familias)
        {
            PdfPCell cellCodigoFamilia = new PdfPCell(new Phrase(familia.getIdFamilia().toString()));
            PdfPCell cellDescricao = new PdfPCell(new Phrase(familia.getDescricao()));
            PdfPCell cellComplemento = new PdfPCell(new Phrase(familia.getComplemento()));
            PdfPCell cellEndereco = new PdfPCell(new Phrase("Rua: " + familia.getIdEndereco().getRua() + ", Cep: " + familia.getIdEndereco().getCep() + ", Bairro: " + familia.getIdEndereco().getIdBairro().getBairro()));
            table.addCell(cellCodigoFamilia);
            table.addCell(cellDescricao);
            table.addCell(cellComplemento);
            table.addCell(cellEndereco);
        }

        document.add(table);

        Paragraph paragraph = new Paragraph("Possui " + familias.size() + " familias cadastradas.", fontTitulo);

        document.add(paragraph);

    }

}