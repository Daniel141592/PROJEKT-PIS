package pis.projekt.utils;
import org.junit.Test;
import org.junit.Assert;
import pis.projekt.utils.Pair;
public class pairTest {

    @Test
    public void isSameTest1()
    {
        Pair pair1 = new Pair(6, 7);
        Pair pair2 = new Pair(6, 7);
        Assert.assertTrue(Pair.isSame(pair1, pair2));
    }

    @Test
    public void isSameTest2()
    {
        Pair pair1 = new Pair(6, 7);
        Pair pair2 = new Pair(6, 6);
        Assert.assertFalse(Pair.isSame(pair1, pair2));
    }
}
