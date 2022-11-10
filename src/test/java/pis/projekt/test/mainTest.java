package pis.projekt.test;

import org.junit.Assert;
import org.junit.Test;
import pis.projekt.main;
import static org.assertj.core.api.Assertions.*;

public class mainTest {

    @Test
    public void addTest(){
        int z = main.add(1,2);
        assertThat(z).isEqualTo(3);
    }
}
