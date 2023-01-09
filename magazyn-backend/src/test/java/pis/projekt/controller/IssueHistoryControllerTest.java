package pis.projekt.controller;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.assertj.core.api.Assertions;
import org.hamcrest.core.Is;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import pis.projekt.models.Employee;
import pis.projekt.models.Issue;
import pis.projekt.models.IssueHistory;
import pis.projekt.models.responses.EmployeeResponse;
import pis.projekt.security.JwtTokenFilter;
import pis.projekt.services.IssueHistoryService;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@WebMvcTest(IssueHistoryController.class)
@RunWith(SpringRunner.class)
public class IssueHistoryControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private IssueHistoryService issueHistoryService;

    @MockBean
    private JwtTokenFilter jwtTokenFilter;

    final ObjectMapper objectMapper = new ObjectMapper();

    @Test
    public void allIssuesTest() throws Exception{
        List<IssueHistory> issueHistories = new LinkedList<>();
        Employee man = new Employee(1, "marek", "grechuta", "mgrech", "hash", true);
        Employee emp = new Employee(2, "jacek", "kaczmarski", "jkac", "hash", false);
        for (int i = 0; i < 4; i++){
            issueHistories.add(new IssueHistory(i, "name", "desc", man, emp, "status"));
        }

        when(issueHistoryService.findAllIssueHistories()).thenReturn(issueHistories);

        RequestBuilder request = MockMvcRequestBuilders.get("/issuehistories/all");
        MvcResult result = mockMvc.perform(request).andReturn();
        String response = result.getResponse().getContentAsString();
        List<IssueHistory> actual = objectMapper.readValue(response, new TypeReference<List<IssueHistory>>() {});

        Assertions.assertThat(actual.size()).isEqualTo(4);

        when(issueHistoryService.findAllIssueHistories()).thenReturn(new LinkedList<IssueHistory>());

        result = mockMvc.perform(request).andReturn();
        response = result.getResponse().getContentAsString();
        actual = objectMapper.readValue(response, new TypeReference<List<IssueHistory>>() {});

        Assertions.assertThat(actual).isEmpty();
    }

    @Test
    public void getIssueByIdTest() throws Exception{
        Employee man = new Employee(1, "marek", "grechuta", "mgrech", "hash", true);
        Employee emp = new Employee(2, "jacek", "kaczmarski", "jkac", "hash", false);
        IssueHistory issueHistory = new IssueHistory(10, "name", "desc", man, emp, "status");

        when(issueHistoryService.findIssueHistoryById(10)).thenReturn(issueHistory);

        RequestBuilder request = MockMvcRequestBuilders.get("/issuehistories/10");
        MvcResult result = mockMvc.perform(request).andReturn();
        String response = result.getResponse().getContentAsString();
        IssueHistory actual = objectMapper.readValue(response, new TypeReference<IssueHistory>() {});

        Assertions.assertThat(actual.getId()).isEqualTo(10);
        Assertions.assertThat(actual.getDescription()).isEqualTo("desc");

        request = MockMvcRequestBuilders.get("/issuehistories/20");
        result = mockMvc.perform(request).andReturn();
        response = result.getResponse().getContentAsString();

        Assertions.assertThat(response).isEmpty();
    }

    @Test
    public void getIssuesContainsTest() throws Exception{
        List<IssueHistory> issueHistories = new LinkedList<>();
        Employee man = new Employee(1, "marek", "grechuta", "mgrech", "hash", true);
        Employee emp = new Employee(2, "jacek", "kaczmarski", "jkac", "hash", false);
        for (int i = 0; i < 4; i++){
            issueHistories.add(new IssueHistory(i, "name", "desc", man, emp, "status"));
        }

        when(issueHistoryService.findIssueHistoriesByDescriptionContains("descript")).thenReturn(issueHistories);

        RequestBuilder request = MockMvcRequestBuilders.get("/issuehistories/contains?desc=descript");
        MvcResult result = mockMvc.perform(request).andReturn();
        String response = result.getResponse().getContentAsString();

        List<IssueHistory> actual = objectMapper.readValue(response, new TypeReference<List<IssueHistory>>() {});

        Assertions.assertThat(actual.size()).isEqualTo(4);
        Assertions.assertThat(actual.get(3).getId()).isEqualTo(3);


        when(issueHistoryService.findIssueHistoriesByDescriptionContains("adolf")).thenReturn(new ArrayList<IssueHistory>());
        request = MockMvcRequestBuilders.get("/issuehistories/contains?desc=adolf");
        result = mockMvc.perform(request).andReturn();
        response = result.getResponse().getContentAsString();

        actual = objectMapper.readValue(response, new TypeReference<List<IssueHistory>>() {});

        Assertions.assertThat(actual).isEmpty();
    }

    @Test
    public void addIssueTest() throws Exception{
        Employee man = new Employee(1, "marek", "grechuta", "mgrech", "hash", true);
        Employee emp = new Employee(2, "jacek", "kaczmarski", "jkac", "hash", false);
        IssueHistory issueHistory = new IssueHistory(10, "name", "desc", man, emp, "status");

        when(issueHistoryService.addIssueHistory(any())).thenReturn(issueHistory);

        RequestBuilder req = MockMvcRequestBuilders
                .post("/issuehistories/add")
                .content(objectMapper.writeValueAsString(issueHistory))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON);
        String response = mockMvc.perform(req).andReturn().getResponse().getContentAsString();
        IssueHistory retIH = objectMapper.readValue(response, new TypeReference<IssueHistory>() {});

        Assertions.assertThat(retIH.getId()).isEqualTo(10);
        Assertions.assertThat(retIH.getStatus()).isEqualTo("status");
    }

    @Test
    public void deleteIssueHistorySuccessTest() throws Exception{
        Employee man = new Employee(1, "marek", "grechuta", "mgrech", "hash", true);
        Employee emp = new Employee(2, "jacek", "kaczmarski", "jkac", "hash", false);
        IssueHistory issueHistory = new IssueHistory(10, "name", "desc", man, emp, "status");

        when(issueHistoryService.deleteIssueHistory(10)).thenReturn(true);

        RequestBuilder req = MockMvcRequestBuilders
                .post("/issuehistories/delete/10")
                .content(objectMapper.writeValueAsString(issueHistory))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON);
        String response = mockMvc.perform(req).andReturn().getResponse().getContentAsString();
        boolean retValue = objectMapper.readValue(response, new TypeReference<Boolean>() {});

        Assertions.assertThat(retValue).isTrue();
    }

    @Test
    public void deleteIssueHistoryFailTest() throws Exception{
        Employee man = new Employee(1, "marek", "grechuta", "mgrech", "hash", true);
        Employee emp = new Employee(2, "jacek", "kaczmarski", "jkac", "hash", false);
        IssueHistory issueHistory = new IssueHistory(20, "name", "desc", man, emp, "status");

        when(issueHistoryService.deleteIssueHistory(20)).thenReturn(false);

        RequestBuilder req = MockMvcRequestBuilders
                .post("/issuehistories/delete/20")
                .content(objectMapper.writeValueAsString(issueHistory))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON);
        String response = mockMvc.perform(req).andReturn().getResponse().getContentAsString();
        boolean retValue = objectMapper.readValue(response, new TypeReference<Boolean>() {});

        Assertions.assertThat(retValue).isFalse();
    }


}

