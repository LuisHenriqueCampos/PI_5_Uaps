package br.com.pi.report;

import br.com.pi.entidade.Paciente;
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
import java.text.DateFormat;
import java.util.Locale;

public class ReportPaciente {

    private ByteArrayOutputStream output;
    
    public String gerar(String caminho, List<Paciente> pacientes)
    {
        String erro = null;

        try {
            output = new ByteArrayOutputStream();

            Document document = new Document(PageSize.A4.rotate());

            PdfWriter writer = PdfWriter.getInstance(document, output);

            PdfPageHelper helper = new PdfPageHelper(caminho);
            helper.setTitle("Relatório Paciente");

            writer.setPageEvent(helper);
            document.open();

            gerarRelatorio(pacientes, document);
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

    private void gerarRelatorio(List<Paciente> pacientes, Document document) throws Exception {

        Font fontTexto = FontFactory.getFont("Arial", 12);
        Font fontTitulo = FontFactory.getFont("Arial", 12, Font.BOLD);

        String[] cabecalhos = {"Codigo","Nome Paciente","Data de Nascimento","Nome Mãe","Nome Pai","Cns","Telefone","Sexo","Família"};

        PdfPTable table = new PdfPTable(cabecalhos.length);
        table.setWidthPercentage(100);
        //table.setWidths(new float[]{15,30});
        table.setHeaderRows(1);
        
        for (String cabecalho : cabecalhos)
        {
            PdfPCell cell = new PdfPCell(new Phrase(cabecalho, fontTitulo));
            cell.setBackgroundColor(BaseColor.LIGHT_GRAY);
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(cell);
        }
        
        Locale ptBr = new Locale("pt", "BR");

        for (Paciente paciente : pacientes)
        {
            PdfPCell cellCodigo = new PdfPCell(new Phrase(paciente.getIdPessoaPaciente().toString()));
            PdfPCell cellNomePaciente = new PdfPCell(new Phrase(paciente.getPessoa().getNome()));
            PdfPCell cellDataNascimento = new PdfPCell(new Phrase(DateFormat.getDateInstance(DateFormat.SHORT,ptBr).format(paciente.getDataNascimento()), fontTexto));
            PdfPCell cellNomeMae = new PdfPCell(new Phrase(paciente.getNomeMae().toString()));
            PdfPCell cellNomePai = new PdfPCell(new Phrase(paciente.getNomePai().toString()));
            PdfPCell cellCns = new PdfPCell(new Phrase(String.valueOf(paciente.getCns())));
            PdfPCell cellTelefone = new PdfPCell(new Phrase(paciente.getTelefone()));
            PdfPCell cellSexo = new PdfPCell(new Phrase(paciente.getIdSexo().getDescricao()));
            PdfPCell cellFamilia = new PdfPCell(new Phrase(paciente.getIdFamilia().getDescricao()));
            
            table.addCell(cellCodigo);
            table.addCell(cellNomePaciente);
            table.addCell(cellDataNascimento);
            table.addCell(cellNomeMae);
            table.addCell(cellNomePai);
            table.addCell(cellCns);
            table.addCell(cellTelefone);
            table.addCell(cellSexo);
            table.addCell(cellCns);
        }

        document.add(table);

        Paragraph paragraph = new Paragraph("Possui " + pacientes.size() + " pacientes cadastrados.", fontTitulo);

        document.add(paragraph);
    }
}