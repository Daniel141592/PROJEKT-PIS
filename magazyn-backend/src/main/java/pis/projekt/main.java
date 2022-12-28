package pis.projekt;

import pis.projekt.services.Magazine;
import pis.projekt.services.Report;
import pis.projekt.services.Section;

import org.apache.pdfbox.pdmodel.PDDocument;
import java.io.IOException;
import java.util.Vector;

public class main {

    public static int add(int x, int y)
    {
        return x+y;
    }

    public static void main(String[] args) throws IOException {

        System.out.println("dupa");
        Vector<Section> sections = new Vector<Section>();
        Section sect = new Section();
        for (int i = 0; i < 4; i++) {
            sections.add(sect);
        }
        Magazine mag = new Magazine(7, "benedict", 21, 37, sections);
        Report rep = new Report(mag);
        /*PDDocument doc = new PDDocument();

        doc.save("blank.pdf");

        System.out.println("PDF created");

        doc.close();*/
    }
}
