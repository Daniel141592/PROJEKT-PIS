package pis.projekt.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pis.projekt.models.Employee;

import java.util.List;

public interface IEmployeeRepository extends JpaRepository<Employee, Integer> {
    Employee findEmployeeById(Integer empId);

    Employee findEmployeeByLogin(String login);

    List<Employee> findEmployeeByName(String firstname);

    List<Employee> findEmployeeBySurname(String lastname);

    List<Employee> findEmployeesByIsManager(boolean isManager);

}
