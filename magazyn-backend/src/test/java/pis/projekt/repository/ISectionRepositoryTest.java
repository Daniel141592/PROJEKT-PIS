package pis.projekt.repository;

import org.assertj.core.api.Assertions;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;
import pis.projekt.Config;
import pis.projekt.models.Magazine;
import pis.projekt.models.Product;
import pis.projekt.models.Section;

import java.util.ArrayList;
import java.util.List;

@RunWith(SpringRunner.class)
@Import({Config.class})
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
@SpringBootTest
@AutoConfigureTestDatabase
public class ISectionRepositoryTest {

    @Autowired
    ISectionRepository SR;

    @Autowired
    IMagazineRepository MR;

    @Autowired
    IProductRepository PR;

    @Before
    @NotFound(action = NotFoundAction.IGNORE)
    public void init(){
        Product pr1 = new Product(1, "nicotine", 7, 8, 10);
        Product pr2 = new Product(2, "caffeine", 3, 4, 5);
        Product pr3 = new Product(3, "gaminate", 6, 7, 8);
        Product pr4 = new Product(4, "sriracha", 1, 2, 3);
        PR.save(pr1);
        PR.save(pr2);
        PR.save(pr3);
        PR.save(pr4);

        List<Section> secs = new ArrayList<Section>();
        Magazine mg1 = new Magazine(1, "wienerberger", 5, 6, secs);
        Magazine mg2 = new Magazine(2, "forczek", 2, 3, secs);
        MR.save(mg1);
        MR.save(mg2);

        Section sec1 = new Section(mg1, pr1, "s1", 1, 2, 3, 4, 5);
        Section sec2 = new Section(mg1, pr2, "s2", 2, 3, 4, 5, 6);
        Section sec3 = new Section(mg2, pr1, "s3", 3, 4, 5, 6, 7);
        Section sec4 = new Section(mg2, pr2, "s4", 4, 5, 6, 7, 8);
        SR.save(sec1);
        SR.save(sec2);
        SR.save(sec3);
        SR.save(sec4);
    }

    @Test
    public void findSectionByIdTest(){
        Assertions.assertThat(SR.findSectionById(1).getLength())
                .isEqualTo(3);
        Assertions.assertThat(SR.findSectionById(5))
                .isNull();
    }

    @Test
    public void findSectionsByMagazine_IdTest(){
        Assertions.assertThat(SR.findSectionsByMagazine_Id(1).size())
                .isEqualTo(2);
        Assertions.assertThat(SR.findSectionsByMagazine_Id(2).get(1).getName())
                .isEqualTo("s4");
        Assertions.assertThat(SR.findSectionsByMagazine_Id(4))
                .isEmpty();
    }

    @Test
    public void findSectionsByProduct_IdTest(){
        Assertions.assertThat(SR.findSectionsByProduct_Id(1).size())
                .isEqualTo(2);
        Assertions.assertThat(SR.findSectionsByProduct_Id(2).get(0).getAmount())
                .isEqualTo(2);
        Assertions.assertThat(SR.findSectionsByProduct_Id(3))
                .isEmpty();
    }
}
