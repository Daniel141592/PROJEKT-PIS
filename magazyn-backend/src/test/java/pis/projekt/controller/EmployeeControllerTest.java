package pis.projekt.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.ServletOutputStream;
import org.json.JSONPointerException;
import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import pis.projekt.models.Employee;
import pis.projekt.models.requests.LoginRequest;
import pis.projekt.models.responses.EmployeeResponse;
import pis.projekt.models.responses.LoginResponse;
import pis.projekt.services.EmployeeService;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(EmployeeController.class)
@RunWith(SpringRunner.class)
public class EmployeeControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
//    @Autowired
    private EmployeeService employeeService;

//    @Autowired
    @InjectMocks
    private EmployeeController employeeController;

    final ObjectMapper objectMapper = new ObjectMapper();

    private static String mapToJson(Object object) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.writeValueAsString(object);
    }

    @Test
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
        LoginRequest loginRequest = new LoginRequest("name", "password");

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
        Employee employee = new Employee(20, "adam", "sudol", "sadam", "hash", false);
        when(employeeService.login(any())).thenReturn(employee);
        when(employeeService.generateToken(any())).thenReturn("token");
        LoginRequest loginRequest = new LoginRequest("name", "password");

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
