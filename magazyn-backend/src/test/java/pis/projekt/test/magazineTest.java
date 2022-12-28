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
}
