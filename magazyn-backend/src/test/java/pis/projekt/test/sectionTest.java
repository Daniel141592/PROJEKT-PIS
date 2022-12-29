package pis.projekt.test;

import org.junit.Test;
import org.junit.Assert;
import pis.projekt.models.Product;
import pis.projekt.services.MagazineService;
import pis.projekt.services.SectionService;
import pis.projekt.utils.Pair;

public class sectionTest {

    @Test
    public void calcMaxCapacityTest1()
    {
        Pair p1 = new Pair(0, 0);
        Pair p2 = new Pair(0, 6);
        Pair p3 = new Pair(5, 6);
        Pair p4 = new Pair(5, 0);
        Pair[] cords = new Pair[]{p1, p2, p3, p4};
        MagazineService magazineService = new MagazineService();
        Product prod = new Product("product", 2, 3, 4);
        SectionService sect = new SectionService(cords,"Name", prod, magazineService);
        Assert.assertEquals(16, sect.calcMaxCapacity());
    }

    @Test
    public void calcMaxCapacityTest2()
    {
        Pair p1 = new Pair(5, 0);
        Pair p2 = new Pair(5, 6);
        Pair p3 = new Pair(0, 6);
        Pair p4 = new Pair(0, 0);
        Pair[] cords = new Pair[]{p1, p2, p3, p4};
        MagazineService magazineService = new MagazineService();
        Product prod = new Product("product", 2, 3, 4);
        SectionService sect = new SectionService(cords,"Name", prod, magazineService);
        Assert.assertEquals(16, sect.calcMaxCapacity());
    }

    @Test
    public void calcMaxCapacityTest3()
    {
        Pair p1 = new Pair(0, 0);
        Pair p2 = new Pair(0, 7);
        Pair p3 = new Pair(3, 7);
        Pair p4 = new Pair(3, 0);
        Pair[] cords = new Pair[]{p1, p2, p3, p4};
        MagazineService magazineService = new MagazineService();
        Product prod = new Product("product", 2, 3, 2);
        SectionService sect = new SectionService(cords,"Name", prod, magazineService);
        Assert.assertEquals(6, sect.calcMaxCapacity());
    }

    @Test
    public void containsPointTest1()
    {
        Pair p1 = new Pair(0, 0);
        Pair p2 = new Pair(0, 7);
        Pair p3 = new Pair(3, 7);
        Pair p4 = new Pair(3, 0);
        Pair[] cords = new Pair[]{p1, p2, p3, p4};
        MagazineService magazineService = new MagazineService();
        Product prod = new Product("product", 2, 3, 2);
        SectionService sect = new SectionService(cords,"Name", prod, magazineService);
        Pair point = new Pair(2, 2);
        Assert.assertTrue(sect.containsPoint(point));
    }

    @Test
    public void containsPointTest2()
    {
        Pair p1 = new Pair(0, 0);
        Pair p2 = new Pair(0, 7);
        Pair p3 = new Pair(3, 7);
        Pair p4 = new Pair(3, 0);
        Pair[] cords = new Pair[]{p1, p2, p3, p4};
        MagazineService magazineService = new MagazineService();
        Product prod = new Product("product", 2, 3, 2);
        SectionService sect = new SectionService(cords,"Name", prod, magazineService);
        Pair point = new Pair(3, 7);
        Assert.assertFalse(sect.containsPoint(point));
    }

    @Test
    public void calcAreaTest1()
    {
        Pair p1 = new Pair(0, 0);
        Pair p2 = new Pair(0, 7);
        Pair p3 = new Pair(3, 7);
        Pair p4 = new Pair(3, 0);
        Pair[] cords = new Pair[]{p1, p2, p3, p4};
        MagazineService magazineService = new MagazineService();
        Product prod = new Product("product", 2, 3, 2);
        SectionService sect = new SectionService(cords,"Name", prod, magazineService);
        Pair point = new Pair(3, 7);
        Assert.assertEquals(21, sect.calcArea(), 0);
    }

    @Test
    public void calcAreaTest2()
    {
        Pair p1 = new Pair(6, 6);
        Pair p2 = new Pair(6, 13);
        Pair p3 = new Pair(9, 13);
        Pair p4 = new Pair(9, 6);
        Pair[] cords = new Pair[]{p1, p2, p3, p4};
        MagazineService magazineService = new MagazineService();
        Product prod = new Product("product", 2, 3, 2);
        SectionService sect = new SectionService(cords,"Name", prod, magazineService);
        Pair point = new Pair(3, 7);
        Assert.assertEquals(21, sect.calcArea(), 0);
    }

    @Test
    public void getCapacityTest1()
    {
        Pair p1 = new Pair(0, 0);
        Pair p2 = new Pair(0, 6);
        Pair p3 = new Pair(5, 6);
        Pair p4 = new Pair(5, 0);
        Pair[] cords = new Pair[]{p1, p2, p3, p4};
        MagazineService magazineService = new MagazineService();
        Product prod = new Product("product", 2, 3, 4);
        SectionService sect = new SectionService(cords,"Name", prod, magazineService);
        for (int i=0; i<20; i++)
        {
            sect.addProduct();
        }
        Assert.assertEquals(16, sect.getAmount());
    }

    @Test
    public void getCapacityTest2()
    {
        Pair p1 = new Pair(0, 0);
        Pair p2 = new Pair(0, 6);
        Pair p3 = new Pair(5, 6);
        Pair p4 = new Pair(5, 0);
        Pair[] cords = new Pair[]{p1, p2, p3, p4};
        MagazineService magazineService = new MagazineService();
        Product prod = new Product("product", 2, 3, 4);
        SectionService sect = new SectionService(cords,"Name", prod, magazineService);
        sect.setAmount(2137);
        Assert.assertEquals(2137, sect.getAmount());
    }

    @Test
    public void setCordsTest()
    {
        Pair p1 = new Pair(0, 0);
        Pair p2 = new Pair(0, 6);
        Pair p3 = new Pair(5, 6);
        Pair p4 = new Pair(5, 0);
        Pair[] cords = new Pair[]{p1, p2, p3, p4};
        MagazineService magazineService = new MagazineService();
        Product prod = new Product("product", 2, 3, 4);
        SectionService sect = new SectionService(cords,"Name", prod, magazineService);
        sect.setCords(1, 2, 1);
        Assert.assertEquals(2, sect.getCords()[1].first);
        Assert.assertEquals(1, sect.getCords()[1].second);
    }

    @Test
    public void addProductTest()
    {
        Pair p1 = new Pair(0, 0);
        Pair p2 = new Pair(0, 20);
        Pair p3 = new Pair(30, 20);
        Pair p4 = new Pair(30, 0);
        Pair[] cords = new Pair[]{p1, p2, p3, p4};
        MagazineService magazineService = new MagazineService();
        Product prod = new Product("product", 2, 3, 4);
        SectionService sect = new SectionService(cords,"Name", prod, magazineService);
        for (int i=0; i<1000; i++)
        {
            sect.addProduct();
        }
        Assert.assertEquals(400, sect.getAmount());
    }
}
