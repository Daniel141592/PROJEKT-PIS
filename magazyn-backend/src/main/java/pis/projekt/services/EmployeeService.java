package pis.projekt.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pis.projekt.interfaces.IEmployeeService;
import pis.projekt.models.Employee;
import pis.projekt.repository.IEmployeeRepository;

import java.util.List;

@Service
public class EmployeeService implements IEmployeeService {
    @Autowired
    private IEmployeeRepository employeeRepository;

    @Override
    public List<Employee> findAllEmployees() {
        return employeeRepository.findAll();
    }

    @Override
    public Employee findEmployeeById(Integer empId) {
        return employeeRepository.findEmployeeById(empId);
    }

    @Override
    public List<Employee> findEmployeeByFirstName(String firstname) {
        return employeeRepository.findEmployeeByName(firstname);
    }

    @Override
    public List<Employee> findEmployeeByLastName(String lastname) {
        return employeeRepository.findEmployeeBySurname(lastname);
    }
}
