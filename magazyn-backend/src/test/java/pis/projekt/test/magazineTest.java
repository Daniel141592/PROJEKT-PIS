package pis.projekt.test;
import org.junit.Test;
import org.junit.Assert;
import pis.projekt.models.Product;
import pis.projekt.services.Magazine;
import pis.projekt.services.Section;
import pis.projekt.utils.Pair;

import java.util.Arrays;
import java.util.Vector;

import static org.assertj.core.api.Assertions.*;

public class magazineTest {

    @Test
    public void emptyConstructorTest()
    {
        Magazine emptyMag = new Magazine();
        Pair zerosPair = new Pair();
        Assert.assertEquals(0, emptyMag.getId());
        Assert.assertEquals("", emptyMag.getName());
        Assert.assertEquals(zerosPair.first, emptyMag.getDimensions().first);
        Assert.assertEquals(zerosPair.second, emptyMag.getDimensions().second);
    }

    @Test
    public void basicConstructorTest()
    {
        Magazine mag = new Magazine(7, "benedict", 21, 37);
        Pair testPair = new Pair(21, 37);
        Assert.assertEquals(7, mag.getId());
        Assert.assertEquals("benedict", mag.getName());
        Assert.assertEquals(testPair.first, mag.getDimensions().first);
        Assert.assertEquals(testPair.second, mag.getDimensions().second);
    }

    @Test
    public void oneSectionConstructorTest()
    {
        Section sect = new Section();
        Magazine mag = new Magazine(7, "benedict", 21, 37, sect);
        Assert.assertEquals(7, mag.getId());
        Assert.assertEquals("benedict", mag.getName());
        Assert.assertEquals(21, mag.getDimensions().first);
        Assert.assertEquals(37, mag.getDimensions().second);
        Assert.assertEquals(1, mag.getSections().size());
    }

    @Test
    public void sectionsVectorConstructorTest()
    {
        Vector<Section> sections = new Vector<Section>();
        Section sect = new Section();
        for (int i=0; i<4; i++)
        {
            sections.add(sect);
        }
        Magazine mag = new Magazine(7, "benedict", 21, 37, sections);
        Assert.assertEquals(7, mag.getId());
        Assert.assertEquals("benedict", mag.getName());
        Assert.assertEquals(21, mag.getDimensions().first);
        Assert.assertEquals(37, mag.getDimensions().second);
        Assert.assertEquals(4, mag.getSections().size());
    }

    @Test
    public void sectionsEmptyVectorConstructorTest()
    {
        Vector<Section> sections = new Vector<Section>();
        Magazine mag = new Magazine(7, "benedict", 21, 37, sections);
        Assert.assertEquals(7, mag.getId());
        Assert.assertEquals("benedict", mag.getName());
        Assert.assertEquals(21, mag.getDimensions().first);
        Assert.assertEquals(37, mag.getDimensions().second);
        Assert.assertEquals(0, mag.getSections().size());
    }

    @Test
    public void addSectionComplexTest()
    {
        Magazine mag = new Magazine(7, "benedict", 21, 37);

        Section sect1 = new Section();
        sect1.setCords(0, 0, 0);
        sect1.setCords(1, 0, 3);
        sect1.setCords(2, 4, 3);
        sect1.setCords(3, 4, 0);
        Assert.assertTrue(mag.addSection(sect1));
        Assert.assertFalse(mag.addSection(sect1));

        Section sect2 = new Section();
        sect2.setCords(0, 1, 2);
        sect2.setCords(1, 1, 3);
        sect2.setCords(2, 4, 3);
        sect2.setCords(3, 4, 2);
        Assert.assertFalse(mag.addSection(sect2));

        Section sect3 = new Section();
        sect3.setCords(0, 4, 3);
        sect3.setCords(1, 4, 4);
        sect3.setCords(2, 5, 4);
        sect3.setCords(3, 5, 3);
        Assert.assertTrue(mag.addSection(sect3));

        Section sect4 = new Section();
        sect4.setCords(0, 4, 0);
        sect4.setCords(1, 4, 3);
        sect4.setCords(2, 6, 3);
        sect4.setCords(3, 6, 0);
        Assert.assertTrue(mag.addSection(sect4));

        Section sect5 = new Section();
        sect5.setCords(0, 3, 2);
        sect5.setCords(1, 3, 4);
        sect5.setCords(2, 5, 4);
        sect5.setCords(3, 5, 2);
        Assert.assertFalse(mag.addSection(sect5));

        Assert.assertEquals(3, mag.getSectionsAmount());
    }

    @Test
    public void calcSpaceTest()
    {
        Magazine mag = new Magazine(7, "benedict", 6, 9);
        Assert.assertEquals(54, mag.calcSpace(), 0);
    }

    @Test
    public void calcEmptySpaceTest()
    {
        Magazine mag = new Magazine(7, "benedict", 21, 37);

        Section sect1 = new Section();
        sect1.setCords(0, 0, 0);
        sect1.setCords(1, 0, 3);
        sect1.setCords(2, 4, 3);
        sect1.setCords(3, 4, 0);
        mag.addSection(sect1);

        Section sect3 = new Section();
        sect3.setCords(0, 4, 3);
        sect3.setCords(1, 4, 4);
        sect3.setCords(2, 5, 4);
        sect3.setCords(3, 5, 3);
        mag.addSection(sect3);

        Section sect4 = new Section();
        sect4.setCords(0, 4, 0);
        sect4.setCords(1, 4, 3);
        sect4.setCords(2, 6, 3);
        sect4.setCords(3, 6, 0);
        mag.addSection(sect4);

        Assert.assertEquals(758, mag.calcEmptySpace(false), 0);
        Assert.assertEquals(0.9755469755, mag.calcEmptySpace(true), 0.005);
    }

    @Test
    public void getProductAmountTest1()
    {
        Magazine mag = new Magazine(7, "benedict", 210, 370);

        Product beer = new Product("beer", 3, 4, 4);
        Product notBeer = new Product("notbeer", 3, 4, 4);

        Section sect1 = new Section();
        sect1.setProduct(beer);
        sect1.setCords(0, 0, 0);
        sect1.setCords(1, 0, 10);
        sect1.setCords(2, 10, 10);
        sect1.setCords(3, 10, 0);
        for (int i=0; i<5; i++)
        {
            sect1.addProduct();
        }
        mag.addSection(sect1);

        Section sect2 = new Section();
        sect2.setProduct(notBeer);
        sect2.setCords(0, 10, 10);
        sect2.setCords(1, 10, 20);
        sect2.setCords(2, 20, 20);
        sect2.setCords(3, 20, 10);
        for (int i=0; i<5; i++)
        {
            sect2.addProduct();
        }
        mag.addSection(sect2);

        Section sect3 = new Section();
        sect3.setProduct(beer);
        sect3.setCords(0, 40, 40);
        sect3.setCords(1, 40, 50);
        sect3.setCords(2, 50, 50);
        sect3.setCords(3, 50, 40);
        for (int i=0; i<2; i++)
        {
            sect3.addProduct();
        }
        mag.addSection(sect3);
        Assert.assertEquals(6, mag.getProductAmount(beer));
        Assert.assertEquals(4, mag.getProductAmount(notBeer));
    }
}
