/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package br.com.pi.report;

import br.com.pi.entidade.Medicoenfermeira;
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
public class ReportMedico {
    private ByteArrayOutputStream output;
    
    public String gerar(String caminho, List<Medicoenfermeira> postos)
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

    private void gerarRelatorio(List<Medicoenfermeira> medicos, Document document) throws Exception {

        Font fontTexto = FontFactory.getFont("Arial", 12);
        Font fontTitulo = FontFactory.getFont("Arial", 12, Font.BOLD);

        String[] cabecalhos = {"Nome","Registro","Assinatura","Atribuição"};

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

        for (Medicoenfermeira medico : medicos) {
            PdfPCell cellNomeMedico = new PdfPCell(new Phrase(medico.getPessoa().getNome(), fontTexto));
            PdfPCell cellRegistro = new PdfPCell(new Phrase(String.valueOf(medico.getRegistro()), fontTexto));
            PdfPCell cellAssinatura = new PdfPCell(new Phrase(medico.getAssinatura(), fontTexto));
            PdfPCell cellAtribuicao = new PdfPCell(new Phrase(medico.getIdAtribuicao().getDescricao(), fontTexto));
            
            table.addCell(cellNomeMedico);
            table.addCell(cellRegistro);
            table.addCell(cellAssinatura);
            table.addCell(cellAtribuicao);
        }

        document.add(table);

        Paragraph paragraph = new Paragraph("Possui " + medicos.size() + "medicos cadastradas.", fontTitulo);

        document.add(paragraph);

    }

}