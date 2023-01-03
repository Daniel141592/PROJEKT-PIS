package pis.projekt.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pis.projekt.interfaces.IEmployeeService;
import pis.projekt.models.Employee;
import pis.projekt.requests.LoginRequest;
import pis.projekt.responses.LoginResponse;

import java.util.List;

@RestController
@RequestMapping("/account")
@CrossOrigin(origins = "http://localhost:5173")
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

    @GetMapping("/all")
    public List<Employee> allEmployees() {
        return employeeService.findAllEmployees();
    }
}
