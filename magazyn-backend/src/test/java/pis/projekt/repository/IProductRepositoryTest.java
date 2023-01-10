package pis.projekt.repository;

import org.assertj.core.api.Assertions;
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
import pis.projekt.models.Product;

@RunWith(SpringRunner.class)
@Import({Config.class})
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
@SpringBootTest
@AutoConfigureTestDatabase
public class IProductRepositoryTest {

    @Autowired
    IProductRepository PR;

    @Before
    public void init(){
        PR.save(new Product(1, "nicotine", 7, 8, 10));
        PR.save(new Product(2, "caffeine", 3, 4, 5));
        PR.save(new Product(3, "gaminate", 6, 7, 8));
        PR.save(new Product(4, "sriracha", 1, 2, 3));
    }

    @Test
    public void findProductByIdTest(){
        Assertions.assertThat(PR.findProductById(1).getName())
                .isEqualTo("nicotine");
        Assertions.assertThat(PR.findProductById(5))
                .isNull();
    }

    @Test
    public void findProductsByNameContainingTest(){
        Assertions.assertThat(PR.findProductsByNameContaining("c").size())
                .isEqualTo(3);
        Assertions.assertThat(PR.findProductsByNameContaining("C").size())
                .isEqualTo(0);
        Assertions.assertThat(PR.findProductsByNameContaining("aminat").get(0).getName())
                .isEqualTo("gaminate");
    }
}
