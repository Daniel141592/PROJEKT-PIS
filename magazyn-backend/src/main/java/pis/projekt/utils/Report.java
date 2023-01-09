package pis.projekt.utils;

import org.apache.pdfbox.io.IOUtils;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDType0Font;
import org.apache.pdfbox.pdmodel.font.PDType1Font;

import pis.projekt.models.Magazine;
import pis.projekt.models.ReportModel;
import pis.projekt.repository.IReportModelRepository;
import pis.projekt.services.MagazineService;
import pis.projekt.models.Section;
import pis.projekt.models.Product;
import pis.projekt.services.ReportModelService;


import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Blob;
import java.time.format.DateTimeFormatter;
import java.time.LocalDateTime;
import java.util.Vector;

public class Report{

//    @Value("${elastic.cloudId}")
//    private String cloudId;
//    @Value("${elastic.username}")
//    private String username;
//    @Value("${elastic.password}")
//    private String password;

    private String name;
    private PDDocument reportDocument;
    private String reportText;
    private PDPage reportPage;
    private PDPageContentStream contentStream;
    private String absolutePath;

    public Report(Magazine magazine) throws IOException {

        reportDocument = new PDDocument();

        LocalDateTime now = LocalDateTime.now();

        createNameForPDF(magazine, now);

        createAnEmptyShellOfAPDF();

        fillPDFWithContent(magazine, now);

        //Look what you made me do... You made me dismember my child and scatter his limbs ...
        //Now look at my beautiful child, what it has become... Does this satisfy you?

    };

    private void createNameForPDF(Magazine magazine, LocalDateTime now){
        DateTimeFormatter dtf_name = DateTimeFormatter.ofPattern("yyyy_MM_dd__HH_mm_ss");
        name = "Raport_" + magazine.getName() + "_" + dtf_name.format(now) + ".pdf";
    }

    private void createAnEmptyShellOfAPDF() throws IOException{
        PDDocument doc = new PDDocument();
        PDPage page = new PDPage();
        doc.addPage(page);
        doc.save(name);
        doc.close();
    }

    private void fillPDFWithContent(Magazine magazine, LocalDateTime now) throws IOException{
        File file = new File(name);
        absolutePath = file.getAbsolutePath();
        reportDocument = PDDocument.load(file);
        reportPage = reportDocument.getPage(0);

        String fontDirectory = "src/main/resources/fonts/arial.ttf";
        PDType0Font font = PDType0Font.load(reportDocument, new File(fontDirectory));

        contentStream = new PDPageContentStream(reportDocument, reportPage);
        contentStream.setFont(font,20);
        contentStream.beginText();
        contentStream.newLineAtOffset(20, 750);
        contentStream.setLeading(15f);
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
        addNewTextLine("Raport z magazynu: " + magazine.getName() + " z " + dtf.format(now));
        contentStream.setFont(font,14);
        addNewTextLine("Nazwa magazynu: " + magazine.getName());
        addNewTextLine("ID magazynu: " + Integer.toString(magazine.getId()));
        addNewTextLine("Wymiary magazynu: " + Integer.toString(magazine.getLength()) + 'x' + Integer.toString(magazine.getWidth()));
        addNewTextLine("Liczba sekcji: " + Integer.toString(magazine.getSections().size()));
        addNewTextLine("Wolne miejsce: " + Double.toString(MagazineService.calcEmptySpace(magazine, false )));
        addNewTextLine("Wolne miejsce (w procentach): " + Double.toString(MagazineService.calcEmptySpace(magazine, true )) + "%");
        contentStream.newLine();
        addNewTextLine("Zawartość magazynu: ");
        contentStream.setFont(font,14);

        Vector<Product> productVector = MagazineService.getProductVector(magazine);

        for(Product p: productVector){
            addNewTextLine("- " + p.getName() + ": " + " Sekcje - " + MagazineService.getProductSections(magazine, p) + "  Całkowita ilość - " + MagazineService.getProductAmount(magazine, p));
        }

        contentStream.endText();
        contentStream.close();
        reportDocument.save(name);
        reportDocument.close();
    }

    private void addNewTextLine(String text) throws IOException{
        contentStream.showText(text);
        contentStream.newLine();
    }

    public String getName() {
        return name;
    }

    public String getAbsolutePath() {
        return absolutePath;
    }

    public ReportModel addReportToDB(IReportModelRepository reportModelRepository, String c, String u, String p){
        ElasticConnector elastic = new ElasticConnector(c, u, p);
        try {
            FileInputStream pdfStream = new FileInputStream(this.getAbsolutePath());
            byte[] pdfBytes = IOUtils.toByteArray(pdfStream);

            elastic.indexPDF(pdfBytes);
            return reportModelRepository.save(new ReportModel(null, pdfBytes));
            //return reportModelRepository.save(new ReportModel(null, pdfBytes));
        }catch (Exception e){System.out.println(e);}

        return null;

    }
}
