package pis.projekt.services;


import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;

public class Report {
    private PDDocument reportDocument;
    private String reportText;
    private PDPage reportPage;
    private PDPageContentStream contentStream;

}
