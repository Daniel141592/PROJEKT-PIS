package pis.projekt.repository;

import org.assertj.core.api.Assertions;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;
import pis.projekt.Config;
import pis.projekt.models.Employee;
import pis.projekt.models.Issue;

@DataJpaTest
@RunWith(SpringRunner.class)
@Import({Config.class})
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)

public class IIssueRepositoryTest {

    @Autowired
    IIssueRepository IR;

    @Autowired
    IEmployeeRepository ER;

    @Before
    public void init(){
        Employee emp1 = new Employee(1, "a", "a", "a", "a", false);
        Employee emp2 = new Employee(2, "b", "b", "b", "b", false);
        Employee man1 = new Employee(3, "c", "c", "c", "c", true);
        Employee man2 = new Employee(4, "d", "d", "d", "d", true);
        ER.save(emp1);
        ER.save(emp2);
        ER.save(man1);
        ER.save(man2);

        IR.save(new Issue(1, "shrek", "like an !onion", man1, emp1, "in war"));
        IR.save(new Issue(2, "donkey", "murphy goat", man1, emp2, "farting"));
        IR.save(new Issue(3, "fiona", "goat cirilla", man2, emp1, "elder blood"));
        IR.save(new Issue(4, "dandelion", "it!self", man2, emp2, "fucking joke"));
    }

    @Test
    public void findIssueByIdTest(){
        Assertions.assertThat(IR.findIssueById(1).getStatus())
                .isEqualTo("in war");
        Assertions.assertThat(IR.findIssueById(4).getName())
                .isEqualTo("dandelion");
        Assertions.assertThat(IR.findIssueById(2137))
                .isNull();
    }

    @Test
    public void findIssuesByIssuingManager_IdTest(){
        Assertions.assertThat(IR.findIssuesByIssuingManager_Id(3).size())
                .isEqualTo(2);
        Assertions.assertThat(IR.findIssuesByIssuingManager_Id(3).get(1).getStatus())
                .isEqualTo("farting");
        Assertions.assertThat(IR.findIssuesByIssuingManager_Id(1))
                .isEmpty();
    }

    @Test
    public void findIssuesByIssuedEmployee_IdTest(){
        Assertions.assertThat(IR.findIssuesByIssuedEmployee_Id(1).size())
                .isEqualTo(2);
        Assertions.assertThat(IR.findIssuesByIssuedEmployee_Id(2).get(1).getStatus())
                .isEqualTo("fucking joke");
        Assertions.assertThat(IR.findIssuesByIssuedEmployee_Id(3))
                .isEmpty();
    }

    @Test
    public void findIssuesByDescriptionContainsTest(){
        Assertions.assertThat(IR.findIssuesByDescriptionContains("goat").size())
                .isEqualTo(2);
        Assertions.assertThat(IR.findIssuesByDescriptionContains("!").get(1).getName())
                .isEqualTo("dandelion");
        Assertions.assertThat(IR.findIssuesByDescriptionContains("").size())
                .isEqualTo(4);
    }


}
