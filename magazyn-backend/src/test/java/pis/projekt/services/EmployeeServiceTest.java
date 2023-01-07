package pis.projekt.services;

import org.apache.commons.codec.binary.Hex;
import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import pis.projekt.models.Employee;
import pis.projekt.models.requests.LoginRequest;
import pis.projekt.models.requests.RegisterRequest;
import pis.projekt.repository.IEmployeeRepository;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class EmployeeServiceTest {

    @Mock
    IEmployeeRepository ER;

    @InjectMocks
    EmployeeService EService;

    private byte[] hashPassword(String password) {
        try {
            PBEKeySpec pbeKeySpec = new PBEKeySpec(password.toCharArray(), "PISPROJEKT".getBytes(), 8192, 128);
            SecretKeyFactory secretKeyFactory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA256");
            return secretKeyFactory.generateSecret(pbeKeySpec).getEncoded();
        } catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
            return null;
        }
    }

    @Test
    public void findAllEmployeesTest(){
        List<Employee> emps = new ArrayList<>();
        emps.add(new Employee(1, "name", "surname", "login", "hash", true));
        emps.add(new Employee(2, "2name", "2surname", "2login", "2hash", false));
        when(ER.findAll()).thenReturn(emps);

        List<Employee> actual = EService.findAllEmployees();
        Assertions.assertThat(actual.size()).isEqualTo(2);
        Assertions.assertThat(actual.get(1).getIsManager()).isFalse();
    }

    @Test
    public void findEmployeeByIdTest(){
        Employee emp = new Employee(1, "name", "surname", "login", "hash", true);

        when(ER.findEmployeeById(1)).thenReturn(emp);
        Assertions.assertThat(EService.findEmployeeById(1)).isSameAs(emp);
        Assertions.assertThat(EService.findEmployeeById(1).getName()).isEqualTo("name");

        when(ER.findEmployeeById(anyInt())).thenReturn(null);
        Assertions.assertThat(EService.findEmployeeById(2)).isNull();
    }

    @Test
    public void findEmployeeByFirstNameTest(){
        List<Employee> empsName = new ArrayList<>();
        List<Employee> emps2Name = new ArrayList<>();
        empsName.add(new Employee(1, "name", "surname", "login", "hash", true));
        emps2Name.add(new Employee(2, "2name", "2surname", "2login", "2hash", false));
        empsName.add(new Employee(3, "name", "3surname", "3login", "3hash", false));

        when(ER.findEmployeeByName("name")).thenReturn(empsName);
        when(ER.findEmployeeByName("2name")).thenReturn(emps2Name);
        Assertions.assertThat(EService.findEmployeeByFirstName("name")).isSameAs(empsName);
        Assertions.assertThat(EService.findEmployeeByFirstName("2name")).isSameAs(emps2Name);

        when(ER.findEmployeeByName(anyString())).thenReturn(new ArrayList<>());
        Assertions.assertThat(EService.findEmployeeByFirstName("login")).isEmpty();
    }

    @Test
    public void findEmployeeByLastNameTest(){
        List<Employee> empsName = new ArrayList<>();
        List<Employee> emps2Name = new ArrayList<>();
        empsName.add(new Employee(1, "name", "surname", "login", "hash", true));
        emps2Name.add(new Employee(2, "2name", "2surname", "2login", "2hash", false));
        empsName.add(new Employee(3, "3name", "surname", "3login", "3hash", false));

        when(ER.findEmployeeBySurname("surname")).thenReturn(empsName);
        when(ER.findEmployeeBySurname("2surname")).thenReturn(emps2Name);
        Assertions.assertThat(EService.findEmployeeByLastName("surname")).isSameAs(empsName);
        Assertions.assertThat(EService.findEmployeeByLastName("2surname")).isSameAs(emps2Name);

        when(ER.findEmployeeBySurname(anyString())).thenReturn(new ArrayList<>());
        Assertions.assertThat(EService.findEmployeeByLastName("login")).isEmpty();
    }

    @Test
    public void findAllManagersTest(){
        List<Employee> mens = new ArrayList<>();
        List<Employee> emps = new ArrayList<>();
        mens.add(new Employee(1, "name", "surname", "login", "hash", true));
        emps.add(new Employee(2, "2name", "2surname", "2login", "2hash", false));
        mens.add(new Employee(3, "3name", "surname", "3login", "3hash", true));

        when(ER.findEmployeesByIsManager(true)).thenReturn(mens);
        Assertions.assertThat(EService.findAllManagers().size()).isEqualTo(2);
        Assertions.assertThat(EService.findAllManagers()).isSameAs(mens);
    }

    @Test
    public void findEmployeesExcludingManagersTest(){
        List<Employee> mens = new ArrayList<>();
        List<Employee> emps = new ArrayList<>();
        mens.add(new Employee(1, "name", "surname", "login", "hash", true));
        emps.add(new Employee(2, "2name", "2surname", "2login", "2hash", false));
        mens.add(new Employee(3, "3name", "surname", "3login", "3hash", true));

        when(ER.findEmployeesByIsManager(false)).thenReturn(emps);
        Assertions.assertThat(EService.findEmployeesExcludingManagers().size()).isEqualTo(1);
        Assertions.assertThat(EService.findEmployeesExcludingManagers()).isSameAs(emps);
    }

    @Test
    public void loginCorrectTest(){
        byte[] hash = hashPassword("pass");
        String enc = Hex.encodeHexString(hash);
        Employee emp = new Employee(1, "name", "surname", "login", enc, true);
        LoginRequest req = new LoginRequest("name", "pass");

        when(ER.findEmployeeByLogin("name")).thenReturn(emp);
        Assertions.assertThat(EService.login(req)).isSameAs(emp);
    }

    @Test
    public void loginLoginNullTest(){
        LoginRequest req = new LoginRequest(null, true);
        Assertions.assertThat(EService.login(req)).isNull();
    }

    @Test
    public void loginPasswordNullTest(){
        LoginRequest req = new LoginRequest(null, false);
        Assertions.assertThat(EService.login(req)).isNull();
    }

    @Test
    public void loginNoEmployeeInBaseTest(){
        byte[] hash = hashPassword("pass");
        String enc = Hex.encodeHexString(hash);
        LoginRequest req = new LoginRequest("name", "pass");
        Employee emp = new Employee(1, "name", "surname", "login", enc, true);

        when(ER.findEmployeeByLogin(anyString())).thenReturn(null);
        Assertions.assertThat(EService.login(req)).isNull();
    }

    @Test
    public void loginWrongPasswordTest(){
        byte[] hash = hashPassword("pass");
        String enc = Hex.encodeHexString(hash);
        LoginRequest req = new LoginRequest("name", "wrongpass");
        Employee emp = new Employee(1, "name", "surname", "login", enc, true);

        when(ER.findEmployeeByLogin(anyString())).thenReturn(emp);
        Assertions.assertThat(EService.login(req)).isNull();
    }

    @Test
    public void registerTest(){
        RegisterRequest req = new RegisterRequest();
        Assertions.assertThat(EService.register(req)).isTrue();
    }

    @Test
    public void generateTokenTest(){
        byte[] hash = hashPassword("pass");
        String enc = Hex.encodeHexString(hash);
        Employee emp = new Employee(1, "name", "surname", "login", enc, true);
        Assertions.assertThat(EService.generateToken(emp)).isNotBlank();
    }

}
