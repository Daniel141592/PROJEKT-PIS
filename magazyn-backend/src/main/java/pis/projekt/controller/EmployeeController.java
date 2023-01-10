package pis.projekt.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pis.projekt.interfaces.IEmployeeService;
import pis.projekt.models.Employee;
import pis.projekt.models.requests.LoginRequest;
import pis.projekt.models.responses.EmployeeResponse;
import pis.projekt.models.responses.LoginResponse;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/employees")
@CrossOrigin(origins = {"http://localhost:5173", "http://localhost", "http://40.114.226.113/"})
public class EmployeeController {
    private final IEmployeeService employeeService;

    @Autowired
    public EmployeeController(IEmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @PostMapping("/login")
    public LoginResponse login(@RequestBody LoginRequest loginRequest) {
        Employee employee = employeeService.login(loginRequest);
        if (employee == null)
            return new LoginResponse(false, "", "Not logged in");
        return new LoginResponse(true, employeeService.generateToken(employee), "Logged in successfully");
    }

    @GetMapping("{id}")
    public EmployeeResponse getEmployee(@PathVariable Integer id) {
        Employee employee = employeeService.findEmployeeById(id);
        return (employee == null) ? null: new EmployeeResponse(employee);
    }

    @GetMapping("/all")
    public List<EmployeeResponse> allEmployees() {
        List<EmployeeResponse> responses = new ArrayList<>();
        employeeService.findAllEmployees().forEach(employee -> responses.add(new EmployeeResponse(employee)));
        return responses;
    }

    @GetMapping("/all/managers")
    public List<EmployeeResponse> getManagers() {
        List<EmployeeResponse> responses = new ArrayList<>();
        employeeService.findAllManagers().forEach(employee -> responses.add(new EmployeeResponse(employee)));
        return responses;
    }

    @GetMapping("/all/slaves")
    public List<EmployeeResponse> getEmployeesExcludingManagers() {
        List<EmployeeResponse> responses = new ArrayList<>();
        employeeService.findEmployeesExcludingManagers().forEach(
                employee -> responses.add(new EmployeeResponse(employee))
        );
        return responses;
    }
}
