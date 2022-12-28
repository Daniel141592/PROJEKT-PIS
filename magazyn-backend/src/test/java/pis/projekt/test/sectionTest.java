package pis.projekt.test;

import org.junit.Test;
import org.junit.Assert;
import pis.projekt.models.Product;
import pis.projekt.services.Magazine;
import pis.projekt.services.Section;
import pis.projekt.utils.Pair;

import static org.assertj.core.api.Assertions.*;

public class sectionTest {

    @Test
    public void getCapacityTest1()
    {
        Pair p1 = new Pair(0, 0);
        Pair p2 = new Pair(0, 6);
        Pair p3 = new Pair(5, 6);
        Pair p4 = new Pair(5, 0);
        Pair[] cords = new Pair[]{p1, p2, p3, p4};
        Magazine magazine = new Magazine();
        Product prod = new Product("product", 2, 3, 4);
        Section sect = new Section(cords,"Name", prod, magazine);
        Assert.assertEquals(16, sect.getCapacity());
    }

    @Test
    public void calcMaxCapacityTest1()
    {
        Pair p1 = new Pair(0, 0);
        Pair p2 = new Pair(0, 6);
        Pair p3 = new Pair(5, 6);
        Pair p4 = new Pair(5, 0);
        Pair[] cords = new Pair[]{p1, p2, p3, p4};
        Magazine magazine = new Magazine();
        Product prod = new Product("product", 2, 3, 4);
        Section sect = new Section(cords,"Name", prod, magazine);
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
        Magazine magazine = new Magazine();
        Product prod = new Product("product", 2, 3, 4);
        Section sect = new Section(cords,"Name", prod, magazine);
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
        Magazine magazine = new Magazine();
        Product prod = new Product("product", 2, 3, 2);
        Section sect = new Section(cords,"Name", prod, magazine);
        Assert.assertEquals(6, sect.calcMaxCapacity());
    }
}
