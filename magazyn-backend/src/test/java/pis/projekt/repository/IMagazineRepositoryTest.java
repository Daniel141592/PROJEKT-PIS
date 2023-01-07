package pis.projekt.repository;

import org.assertj.core.api.Assertions;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;
import pis.projekt.Config;
import pis.projekt.models.Magazine;
import pis.projekt.models.Section;

import java.util.ArrayList;
import java.util.List;

@DataJpaTest
@RunWith(SpringRunner.class)
@Import({Config.class})
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class IMagazineRepositoryTest {

    @Autowired
    IMagazineRepository MR;

    @Before
    @NotFound(action = NotFoundAction.IGNORE)
    public void init(){
        List<Section> secs = new ArrayList<Section>();
        Magazine mg1 = new Magazine(1, "wienerberger", 5, 6, secs);
        Magazine mg2 = new Magazine(2, "forczelk", 2, 3, secs);
        Magazine mg3 = new Magazine(3, "polibuda", 1, 2, secs);
        Magazine mg4 = new Magazine(4, "elka", 4, 5, secs);
        MR.save(mg1);
        MR.save(mg2);
        MR.save(mg3);
        MR.save(mg4);
    }

    @Test
    public void findMagazineByIdTest(){
        Assertions.assertThat(MR.findMagazineById(1).getName())
                .isEqualTo("wienerberger");
        Assertions.assertThat(MR.findMagazineById(10))
                .isNull();
    }

    @Test
    public void findMagazineByNameContainingTest(){
        Assertions.assertThat(MR.findMagazineByNameContaining("e").size())
                .isEqualTo(3);
        Assertions.assertThat(MR.findMagazineByNameContaining("el").size())
                .isEqualTo(2);
        Assertions.assertThat(MR.findMagazineByNameContaining("unthinkable shit"))
                .isEmpty();
    }
}
