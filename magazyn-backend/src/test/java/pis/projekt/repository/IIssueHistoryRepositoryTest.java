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
import pis.projekt.models.IssueHistory;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.Month;


@DataJpaTest
@RunWith(SpringRunner.class)
@Import({Config.class})
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class IIssueHistoryRepositoryTest {

    @Autowired
    IIssueHistoryRepository HR;

    @Autowired
    IEmployeeRepository ER;

    LocalDateTime ldt1 = LocalDateTime.of(2005, Month.APRIL, 2, 21, 37, 00);
    LocalDateTime ldt2 = LocalDateTime.of(1945, Month.APRIL, 30, 15, 30, 00);

    Timestamp time1 = Timestamp.valueOf(ldt1);
    Timestamp time2 = Timestamp.valueOf(ldt2);

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

        HR.save(new IssueHistory(1, "daddy", "is it issue? depends", man1, emp1, "complicated", time1));
        HR.save(new IssueHistory(2, "mummy", "issue af really", man1, emp2, "really complicated", time1));
        HR.save(new IssueHistory(3, "pis", "stonoga ?depends on us", man2, emp1, "platforma", time2));
        HR.save(new IssueHistory(4, "narodnyj", "kapitalizm wrong", man2, emp2, "droga", time2));
    }

    @Test
    public void ifNotEmptyTest(){
        Assertions.assertThat(HR.findAll().size())
                .isEqualTo(4);
    }

    @Test
    public void findIssueHistoryByIdTest(){
        Assertions.assertThat(HR.findIssueHistoryById(3).getName())
                .isEqualTo("pis");
        Assertions.assertThat(HR.findIssueHistoryById(14))
                .isNull();
    }

    @Test
    public void findIssueHistoriesByIssuingManager_IdTest(){
        Assertions.assertThat(HR.findIssueHistoriesByIssuingManager_Id(3).size())
                .isEqualTo(2);
        Assertions.assertThat(HR.findIssueHistoriesByIssuingManager_Id(3).get(0).getName())
                .isEqualTo("daddy");
        Assertions.assertThat(HR.findIssueHistoriesByIssuingManager_Id(1))
                .isEmpty();
    }

    @Test
    public void findIssueHistoriesByIssuedEmployee_IdTest(){
        Assertions.assertThat(HR.findIssueHistoriesByIssuedEmployee_Id(1).size())
                .isEqualTo(2);
        Assertions.assertThat(HR.findIssueHistoriesByIssuedEmployee_Id(1).get(0).getName())
                .isEqualTo("daddy");
        Assertions.assertThat(HR.findIssueHistoriesByIssuedEmployee_Id(3))
                .isEmpty();
    }

    @Test
    public void findIssueHistoriesByDescriptionContainsTest(){
        Assertions.assertThat(HR.findIssueHistoriesByDescriptionContains("depends").size())
                .isEqualTo(2);
        Assertions.assertThat(HR.findIssueHistoriesByDescriptionContains("depends").get(0).getName())
                .isEqualTo("daddy");
        Assertions.assertThat(HR.findIssueHistoriesByDescriptionContains("issue").size())
                .isEqualTo(2);
        Assertions.assertThat(HR.findIssueHistoriesByDescriptionContains("issue").get(1).getName())
                .isEqualTo("mummy");
        Assertions.assertThat(HR.findIssueHistoriesByDescriptionContains(" ").size())
                .isEqualTo(4);
        Assertions.assertThat(HR.findIssueHistoriesByDescriptionContains("?").size())
                .isEqualTo(2);
    }

    @Test
    public void findIssueHistoryByModifyDateTest(){
        Assertions.assertThat(HR.findIssueHistoriesByModifyDate(time1).size())
                .isEqualTo(2);
        Assertions.assertThat(HR.findIssueHistoriesByModifyDate(time1).get(1).getStatus())
                .isEqualTo("really complicated");
        Assertions.assertThat(HR.findIssueHistoriesByModifyDate(time2).size())
                .isEqualTo(2);
    }

}
