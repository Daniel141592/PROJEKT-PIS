package pis.projekt.services;


import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDType1Font;

import java.io.IOException;
import java.time.format.DateTimeFormatter;
import java.time.LocalDateTime;

public class Report {
    private PDDocument reportDocument;
    private String reportText;
    private PDPage reportPage;
    private PDPageContentStream contentStream;

    public Report(Magazine magazine) throws IOException {
        this.reportDocument = new PDDocument();
        this.reportPage = new PDPage();
        this.contentStream = new PDPageContentStream(this.reportDocument, this.reportPage);
        this.contentStream.setFont(PDType1Font.TIMES_ROMAN,20);
        this.contentStream.newLineAtOffset(20, 450);
        this.contentStream.setLeading(15f);
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();
        this.reportText = "Raport z magazynu: " + magazine.getName() + " z " + dtf.format(now);
        this.contentStream.showText(this.reportText);


        this.contentStream.endText();
        this.contentStream.close();
        this.reportText = "Raport_" + magazine.getName() + "_" + dtf.format(now) + ".pdf";
        this.reportDocument.save(this.reportText);


    };


}
