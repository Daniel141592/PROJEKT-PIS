package pis.projekt.interfaces;

import pis.projekt.models.Employee;
import pis.projekt.models.requests.LoginRequest;
import pis.projekt.models.requests.RegisterRequest;

import java.util.List;

public interface IEmployeeService {

    List<Employee> findAllEmployees();
    Employee findEmployeeById(Integer empId);

    List<Employee> findEmployeeByFirstName(String firstname);

    List<Employee> findEmployeeByLastName(String lastname);

    List<Employee> findAllManagers();

    List<Employee> findEmployeesExcludingManagers();

    Employee findEmployeeByLogin(String login);

    Employee login(LoginRequest loginRequest);

    boolean register(RegisterRequest registerRequest);

    String generateToken(Employee employee);
}
