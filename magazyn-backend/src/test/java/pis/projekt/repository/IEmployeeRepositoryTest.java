package pis.projekt.repository;

import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.AutoConfigureDataJpa;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import pis.projekt.Config;
import pis.projekt.models.Employee;
import pis.projekt.repository.IEmployeeRepository;

@DataJpaTest
@RunWith(SpringRunner.class)
@Import({Config.class})
//@AutoConfigureDataJpa
public class IEmployeeRepositoryTest {

//    @Autowired
//    private TestEntityManager entityManager;

    @Autowired
    IEmployeeRepository employeeRepository;


    @Test
    public void isEmptyTest(){
        Iterable<Employee> employees = employeeRepository.findAll();
        Assertions.assertThat(employees).isEmpty();
    }

    @Test
    public void isNotEmptyTest(){
        employeeRepository.save(new Employee(0, "paweł", "kaliniczenko", "pkal", "chuj", true));
        Assertions.assertThat(employeeRepository.findEmployeeByLogin("pkal").getLogin()).isEqualTo("pkal");
    }

}
