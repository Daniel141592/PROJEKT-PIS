package pis.projekt.services;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.apache.commons.codec.binary.Hex;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pis.projekt.interfaces.IEmployeeService;
import pis.projekt.models.Employee;
import pis.projekt.repository.IEmployeeRepository;
import pis.projekt.models.requests.LoginRequest;
import pis.projekt.models.requests.RegisterRequest;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.Date;
import java.util.List;

@Service
public class EmployeeService implements IEmployeeService {
    @Autowired
    private IEmployeeRepository employeeRepository;
    public static final String SECRET = "YWJkZmhiZGhhZGhncWV0aHRhaGFyZ3NnZ2JhbmEBDPj=";

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

    @Override
    public List<Employee> findAllManagers() {
        return employeeRepository.findEmployeesByIsManager(true);
    }

    @Override
    public List<Employee> findEmployeesExcludingManagers() {
        return employeeRepository.findEmployeesByIsManager(false);
    }

    @Override
    public Employee login(LoginRequest loginRequest) {
        if (loginRequest.getPassword() == null || loginRequest.getPassword().isBlank())
            return null;
        if (loginRequest.getUsername() == null || loginRequest.getUsername().isBlank())
            return null;
        Employee employee = employeeRepository.findEmployeeByLogin(loginRequest.getUsername());
        if (employee == null)
            return null;
        String hashStoredInDb = employee.getPasswordHash();
        String newHash = Hex.encodeHexString(hashPassword(loginRequest.getPassword()));
        if (!newHash.equals(hashStoredInDb))
            return null;
        return employee;
    }

    @Override
    public boolean register(RegisterRequest registerRequest) {
        return true;
    }

    @Override
    public String generateToken(Employee employee) {
        return Jwts.builder()
                .setIssuer("PISPROJEKT")
                .setSubject(employee.getLogin())
                .claim("manager", employee.getIsManager())
                .claim("login", employee.getLogin())
                .claim("password", employee.getPasswordHash())
                .setIssuedAt(new Date())
                .signWith(
                        SignatureAlgorithm.HS256,
                        SECRET.getBytes()
                )
                .compact();
    }

    private byte[] hashPassword(String password) {
        try {
            PBEKeySpec pbeKeySpec = new PBEKeySpec(password.toCharArray(), "PISPROJEKT".getBytes(), 8192, 128);
            SecretKeyFactory secretKeyFactory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA256");
            return secretKeyFactory.generateSecret(pbeKeySpec).getEncoded();
        } catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
            return null;
        }
    }

}
