package pis.projekt.interfaces;

import pis.projekt.models.Employee;

import java.util.List;

public interface IEmployeeService {

    List<Employee> findAllEmployees();
    Employee findEmployeeById(Integer empId);

    List<Employee> findEmployeeByFirstName(String firstname);

    List<Employee> findEmployeeByLastName(String lastname);
}
