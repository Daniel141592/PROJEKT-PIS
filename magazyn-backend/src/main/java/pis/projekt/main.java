package pis.projekt;

import pis.projekt.models.Product;
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

        Product p1 = new Product("cegua",1,2,3);
        Product p2 = new Product("cegua2",2,1,3);

        MagazineService mag = new MagazineService(7, "benedict", 21, 37);

        SectionService sect1 = new SectionService();
        sect1.setCords(0, 0, 0);
        sect1.setCords(1, 0, 3);
        sect1.setCords(2, 4, 3);
        sect1.setCords(3, 4, 0);
        sect1.setProduct(p1);
        sect1.setAmount(2);
        mag.addSection(sect1);

        SectionService sect3 = new SectionService();
        sect3.setCords(0, 4, 3);
        sect3.setCords(1, 4, 4);
        sect3.setCords(2, 5, 4);
        sect3.setCords(3, 5, 3);
        sect3.setProduct(p1);
        sect3.setAmount(3);
        mag.addSection(sect3);

        SectionService sect4 = new SectionService();
        sect4.setCords(0, 4, 0);
        sect4.setCords(1, 4, 3);
        sect4.setCords(2, 6, 3);
        sect4.setCords(3, 6, 0);
        sect4.setProduct(p2);
        sect4.setAmount(1);
        mag.addSection(sect4);

        Report rep = new Report(mag);
        /*PDDocument doc = new PDDocument();

        doc.save("blank.pdf");

        System.out.println("PDF created");

        doc.close();*/
    }
}
