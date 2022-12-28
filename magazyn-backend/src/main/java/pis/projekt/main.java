package pis.projekt;

import pis.projekt.services.MagazineService;
import pis.projekt.services.Report;
import pis.projekt.services.SectionService;

import java.io.IOException;
import java.util.Vector;

public class main {

    public static int add(int x, int y)
    {
        return x+y;
    }

    public static void main(String[] args) throws IOException {

        System.out.println("dupa");
        Vector<SectionService> sectionServices = new Vector<SectionService>();
        SectionService sect = new SectionService();
        for (int i = 0; i < 4; i++) {
            sectionServices.add(sect);
        }
        MagazineService mag = new MagazineService(7, "benedict", 21, 37, sectionServices);
        Report rep = new Report(mag);
        /*PDDocument doc = new PDDocument();

        doc.save("blank.pdf");

        System.out.println("PDF created");

        doc.close();*/
    }
}
