package pis.projekt.controller;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.junit.Before;
import org.junit.Test;
import org.assertj.core.api.Assertions;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import pis.projekt.models.Employee;
import pis.projekt.models.requests.LoginRequest;
import pis.projekt.models.responses.EmployeeResponse;
import pis.projekt.models.responses.LoginResponse;
import pis.projekt.repository.IEmployeeRepository;
import pis.projekt.security.JwtTokenFilter;
import pis.projekt.services.EmployeeService;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;
import static pis.projekt.services.EmployeeService.SECRET;

@WebMvcTest(EmployeeController.class)
@RunWith(SpringRunner.class)
@AutoConfigureMockMvc(addFilters = false)
public class EmployeeControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private EmployeeService employeeService;
    @MockBean
    private JwtTokenFilter jwtTokenFilter;

    final ObjectMapper objectMapper = new ObjectMapper();

    @Test
    @WithMockUser(value = "jplacek")
    public void allEmployeesTest() throws Exception{
        List<Employee> employees = new ArrayList<>();
        for (int i=0; i<10; i++) {
            employees.add(new Employee(i, "name", "surname", "login", "hash", false));
        }

        when(employeeService.findAllEmployees()).thenReturn(employees);
        RequestBuilder request = MockMvcRequestBuilders
                .get("/employees/all");
        MvcResult result = mockMvc.perform(request).andReturn();
        String response =  result.getResponse().getContentAsString();
        List<EmployeeResponse> employeeResponses = objectMapper.readValue(response, new TypeReference<List<EmployeeResponse>>() {});

        Assertions.assertThat(employeeResponses.size()).isEqualTo(10);
        Assertions.assertThat(employeeResponses.get(3).getId()).isEqualTo(3);
    }

    @Test
    public void getManagersTest() throws Exception{
        List<Employee> mans = new ArrayList<>();
        for (int i=0; i<10; i++) {
            mans.add(new Employee(i, "name", "surname", "login", "hash", true));
        }

        when(employeeService.findAllManagers()).thenReturn(mans);

        RequestBuilder req = MockMvcRequestBuilders
                .get("/employees/all/managers");
        MvcResult result = mockMvc.perform(req).andReturn();
        String response = result.getResponse().getContentAsString();
        List<EmployeeResponse> employeeResponses = objectMapper.readValue(response, new TypeReference<List<EmployeeResponse>>() {});

        Assertions.assertThat(employeeResponses.size()).isEqualTo(10);
        Assertions.assertThat(employeeResponses.get(3).getId()).isEqualTo(3);
    }

    @Test
    public void getEmployeeSuccessTest() throws Exception{
        Employee emp = new Employee(7312);

        when(employeeService.findEmployeeById(7312)).thenReturn(emp);

        RequestBuilder req = MockMvcRequestBuilders
                .get("/employees/7312");
        String response = mockMvc.perform(req).andReturn().getResponse().getContentAsString();
        EmployeeResponse employeeResponse = objectMapper.readValue(response, new TypeReference<EmployeeResponse>() {});

        Assertions.assertThat(employeeResponse.getId()).isEqualTo(7312);
    }

    @Test
    public void getEmployeeFailTest() throws Exception{
        when(employeeService.findEmployeeById(anyInt())).thenReturn(null);

        RequestBuilder req = MockMvcRequestBuilders
                .get("/employees/7312");
        String response = mockMvc.perform(req).andReturn().getResponse().getContentAsString();

        Assertions.assertThat(response).isEmpty();
    }

    @Test
    public void loginFailTest() throws Exception{
        when(employeeService.login(any())).thenReturn(null);

        LoginRequest loginRequest = new LoginRequest("jacek", "placek");

        RequestBuilder req = MockMvcRequestBuilders
                .post("/employees/login")
                .content(objectMapper.writeValueAsString(loginRequest))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON);
        String response = mockMvc.perform(req).andReturn().getResponse().getContentAsString();
        LoginResponse loginResponse = objectMapper.readValue(response, new TypeReference<LoginResponse>() {});

        Assertions.assertThat(loginResponse.isSuccess()).isFalse();
        Assertions.assertThat(loginResponse.getToken()).isEqualTo("");
        Assertions.assertThat(loginResponse.getMessage()).isEqualTo("Not logged in");
    }

    @Test
    public void loginSuccessTest() throws Exception{
        Employee EMP = new Employee(1, "jacek", "placek", "jplacek", "hash", true);

        LoginRequest loginRequest = new LoginRequest("jplacek", "hash");
        when(employeeService.login(any())).thenReturn(EMP);
        when(employeeService.generateToken(any())).thenReturn("token");

        RequestBuilder req = MockMvcRequestBuilders
                .post("/employees/login")
                .content(objectMapper.writeValueAsString(loginRequest))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON);
        String response = mockMvc.perform(req).andReturn().getResponse().getContentAsString();
        LoginResponse loginResponse = objectMapper.readValue(response, new TypeReference<LoginResponse>() {});

        Assertions.assertThat(loginResponse.isSuccess()).isTrue();
        Assertions.assertThat(loginResponse.getToken()).isEqualTo("token");
        Assertions.assertThat(loginResponse.getMessage()).isEqualTo("Logged in successfully");
    }

    @Test
    public void getEmployeesExcludingManagersTest() throws Exception{
        List<Employee> mans = new ArrayList<>();
        for (int i=0; i<3; i++) {
            mans.add(new Employee(i, "name", "surname", "login", "hash", true));
        }

        when(employeeService.findEmployeesExcludingManagers()).thenReturn(mans);

        RequestBuilder req = MockMvcRequestBuilders
                .get("/employees/all/slaves");
        String response = mockMvc.perform(req).andReturn().getResponse().getContentAsString();
        List<EmployeeResponse> employeeResponses = objectMapper.readValue(response, new TypeReference<List<EmployeeResponse>>() {});

        Assertions.assertThat(employeeResponses.size()).isEqualTo(3);
        Assertions.assertThat(employeeResponses.get(0).getId()).isEqualTo(0);
        Assertions.assertThat(employeeResponses.get(0).getLogin()).isEqualTo("login");
        Assertions.assertThat(employeeResponses.get(2).getId()).isEqualTo(2);
    }
}
