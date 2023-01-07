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

@DataJpaTest
@RunWith(SpringRunner.class)
@Import({Config.class})
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class IEmployeeRepositoryTest {

    @Autowired
    IEmployeeRepository employeeRepository;

    @Before
    public void init(){
        employeeRepository.save(new Employee(1, "milena", "pieta", "mpiet", "anotherslowo", true));
        employeeRepository.save(new Employee(2, "adam", "sur", "asur", "slowo3", true));
        employeeRepository.save(new Employee(3, "pawel", "kali", "pkali", "mile", false));
    }

    @Test
    public void isEmptyTest(){
        employeeRepository.deleteAll();
        Iterable<Employee> employees = employeeRepository.findAll();
        Assertions.assertThat(employees).isEmpty();
    }

    @Test
    public void isNotEmptyTest(){
        Assertions.assertThat(employeeRepository.findAll().size()).isEqualTo(3);
    }

    @Test
    public void findEmployeeByIdTest(){
        Assertions.assertThat(employeeRepository.findEmployeeById(2).getName()).isEqualTo("adam");
        Assertions.assertThat(employeeRepository.findEmployeeById(2137)).isNull();
    }

    @Test
    public void findEmployeeByLoginTest(){
        Assertions.assertThat(employeeRepository.findEmployeeByLogin("mpiet").getName()).isEqualTo("milena");
        Assertions.assertThat(employeeRepository.findEmployeeByLogin("benedicto")).isNull();
    }

    @Test
    public void findEmployeeByNameTest(){
        Assertions.assertThat(employeeRepository.findEmployeeByName("milena").get(0).getLogin()).isEqualTo("mpiet");
        Assertions.assertThat(employeeRepository.findEmployeeByName("benedicto")).isEmpty();
    }

    @Test
    public void findEmployeeByNameSurname(){
        Assertions.assertThat(employeeRepository.findEmployeeBySurname("kali").get(0).getLogin()).isEqualTo("pkali");
        Assertions.assertThat(employeeRepository.findEmployeeBySurname("benedicto")).isEmpty();
    }

    @Test
    public void findEmployeeByIsManagerTest(){
        Assertions.assertThat(employeeRepository.findEmployeesByIsManager(true).get(1).getLogin()).isEqualTo("asur");
        Assertions.assertThat(employeeRepository.findEmployeesByIsManager(false).get(0).getLogin()).isEqualTo("pkali");
    }

}