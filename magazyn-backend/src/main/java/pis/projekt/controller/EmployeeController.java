package pis.projekt.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pis.projekt.interfaces.IEmployeeService;
import pis.projekt.models.Employee;

import java.util.List;

@RestController
@RequestMapping("/account")
public class EmployeeController {
    private final IEmployeeService employeeService;

    @Autowired
    public EmployeeController(IEmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping("")
    public String test() {
        return "TU POWINIEN BYĆ TWÓJ ULUBIONY MAGAZYN";
    }

    @GetMapping("/login")
    public String login() {
        return "not implemented yet";
    }

    @GetMapping("/all")
    public List<Employee> allEmployees() {
        return employeeService.findAllEmployees();
    }
}
