package br.com.pi.report;

import br.com.pi.entidade.Endereco;
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
public class ReportEndereco
{
    private ByteArrayOutputStream output;
    
    public String gerar(String caminho, List<Endereco> enderecos)
    {
        String erro = null;
        try
        {
            output = new ByteArrayOutputStream();
            Document document = new Document(PageSize.A4.rotate());
            PdfWriter writer = PdfWriter.getInstance(document, output);
            PdfPageHelper helper = new PdfPageHelper(caminho);
            helper.setTitle("Relatório de Atribuições:");
            writer.setPageEvent(helper);
            document.open();
            
            gerarRelatorio(enderecos, document);
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
    
    private void gerarRelatorio(List<Endereco> enderecos, Document document) throws Exception
    {
        Font fontTexto = FontFactory.getFont("Arial", 12);
        Font fontTitulo = FontFactory.getFont("Arial", 12, Font.BOLD);
        
        String[] cabecalhos = {"Código Atribuicao","Descricao", "CEP", "Bairro"};
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
        
        for(Endereco endereco : enderecos)
        {
            PdfPCell cellCodigo = new PdfPCell(new Phrase(endereco.getIdEndereco().toString()));
            PdfPCell cellDescricao = new PdfPCell(new Phrase(endereco.getRua(), fontTexto));
            PdfPCell cellCep = new PdfPCell(new Phrase(endereco.getCep(), fontTexto));
            PdfPCell cellBairro = new PdfPCell(new Phrase(endereco.getIdBairro().getBairro(), fontTexto));
            
            table.addCell(cellCodigo);
            table.addCell(cellDescricao);
            table.addCell(cellCep);
            table.addCell(cellBairro);
        }
        
        document.add(table);
        System.out.println("Adiciona tabela.");
        Paragraph paragraph = new Paragraph("Possui " + enderecos.size() + " enderecos cadastrados");
        document.add(paragraph);
        System.out.println("Adiciona paragrafo.");
    }
}
