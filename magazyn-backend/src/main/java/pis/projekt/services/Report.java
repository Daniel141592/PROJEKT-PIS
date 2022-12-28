package pis.projekt.services;


import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDType1Font;

import java.io.File;
import java.io.IOException;
import java.time.format.DateTimeFormatter;
import java.time.LocalDateTime;

public class Report{
    private String name;
    private PDDocument reportDocument;
    private String reportText;
    private PDPage reportPage;
    private PDPageContentStream contentStream;

    public Report(Magazine magazine) throws IOException {
        reportDocument = new PDDocument();

        DateTimeFormatter dtf_name = DateTimeFormatter.ofPattern("yyyy_MM_dd__HH_mm_ss");
        LocalDateTime now = LocalDateTime.now();

        name = "Raport_" + magazine.getName() + "_" + dtf_name.format(now) + ".pdf";

        PDDocument doc = new PDDocument();
        PDPage page = new PDPage();
        doc.addPage(page);
        doc.save(name);
        doc.close();

        File file = new File(name);
        reportDocument = PDDocument.load(file);

        reportPage = reportDocument.getPage(0);

        contentStream = new PDPageContentStream(reportDocument, reportPage);
        contentStream.setFont(PDType1Font.TIMES_ROMAN,20);
        contentStream.beginText();
        contentStream.newLineAtOffset(20, 750);
        contentStream.setLeading(15f);
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
        reportText = "Raport z magazynu: " + magazine.getName() + " z " + dtf.format(now);
        contentStream.showText(reportText);
        contentStream.setFont(PDType1Font.TIMES_ROMAN,14);
        contentStream.newLine();
        reportText ="Nazwa magazynu: " + magazine.getName();
        contentStream.showText(reportText);
        contentStream.newLine();
        reportText ="ID magazynu: " + Integer.toString(magazine.getId());
        contentStream.showText(reportText);
        contentStream.newLine();
        reportText ="Wymiary magazynu: " + Integer.toString(magazine.getDimensions().first) + 'x' + Integer.toString(magazine.getDimensions().second);
        contentStream.showText(reportText);
        contentStream.newLine();
        reportText ="Liczba sekcji: " + Integer.toString(magazine.getSectionsAmount());
        contentStream.showText(reportText);
        contentStream.newLine();
        reportText ="Wolne miejsce " + Double.toString(magazine.calcEmptySpace(false));
        contentStream.showText(reportText);
        contentStream.newLine();
        reportText ="Wolne miejsce (w procentach) " + Double.toString(magazine.calcEmptySpace(true));
        contentStream.showText(reportText);

        contentStream.endText();
        contentStream.close();
        reportDocument.save(name);
        reportDocument.close();


    };


}
