package pis.projekt.test;
import org.junit.Test;
import org.junit.Assert;
import pis.projekt.models.Product;
import pis.projekt.services.Magazine;
import pis.projekt.services.Section;
import pis.projekt.utils.Pair;
public class productTest {

    @Test
    public void isSameTest1()
    {
        Product prod1 = new Product("beer", 3, 4, 5);
        Product prod2 = new Product("beer", 3, 4, 5);
        Assert.assertTrue(Product.isSame(prod1, prod2));
    }

    @Test
    public void isSameTest2()
    {
        Product prod1 = new Product("beer", 3, 4, 5);
        Product prod2 = new Product("nbeer", 3, 4, 5);
        Assert.assertFalse(Product.isSame(prod1, prod2));
    }
}
