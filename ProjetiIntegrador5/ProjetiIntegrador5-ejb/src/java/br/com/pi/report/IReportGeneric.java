/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package br.com.pi.report;

import com.itextpdf.text.Document;
import java.io.Writer;

/**
 *
 * @author Anderson
 */
public interface IReportGeneric
{
    void createPdf();
    void customisePdf(Document document, String filename);
}