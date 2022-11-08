package pis.projekt.test;

import org.junit.Assert;
import org.junit.Test;
import pis.projekt.main;

public class mainTest {

    @Test
    public void addTest(){
        int z = main.add(1,2);
        Assert.assertEquals(z,3);
    }
}
