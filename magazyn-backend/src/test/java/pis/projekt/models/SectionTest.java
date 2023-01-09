package pis.projekt.models;

import org.junit.Test;
import org.assertj.core.api.Assertions;
import pis.projekt.utils.Pair;

public class SectionTest {

    @Test
    public void GetCordsTest(){
        Section section = new Section(1, null, null, null, 5,
                21, 37, 4, 5);
        Pair[] cords = section.GetCoords();

        Assertions.assertThat(cords[0].first).isEqualTo(4);
        Assertions.assertThat(cords[0].second).isEqualTo(5);
        Assertions.assertThat(cords[1].first).isEqualTo(25);
        Assertions.assertThat(cords[1].second).isEqualTo(5);
        Assertions.assertThat(cords[2].first).isEqualTo(25);
        Assertions.assertThat(cords[2].second).isEqualTo(42);
        Assertions.assertThat(cords[3].first).isEqualTo(4);
        Assertions.assertThat(cords[3].second).isEqualTo(42);
    }
}
