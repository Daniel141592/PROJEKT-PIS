package pis.projekt.controller;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.assertj.core.api.Assertions;
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
import pis.projekt.models.requests.ChangeStatusRequest;
import pis.projekt.services.IssueService;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@WebMvcTest(IssueController.class)
@RunWith(SpringRunner.class)
public class IssueControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private IssueService issueService;

    @InjectMocks
    private IssueController issueController;

    final ObjectMapper objectMapper = new ObjectMapper();

    @Test
    public void allIssuesTest() throws Exception{
        List<Issue> issues = new LinkedList<>();
        Employee man = new Employee(1, "marek", "grechuta", "mgrech", "hash", true);
        Employee emp = new Employee(2, "jacek", "kaczmarski", "jkac", "hash", false);

        for (int i=0; i<5; i++){
            issues.add(new Issue(i, "name", "desc", man, emp, "status"));
        }

        when(issueService.findAllIssues()).thenReturn(issues);

        RequestBuilder req = MockMvcRequestBuilders.get("/issues/all");
        MvcResult result = mockMvc.perform(req).andReturn();
        String response =  result.getResponse().getContentAsString();

        List<Issue> actIssues = objectMapper.readValue(response, new TypeReference<List<Issue>>() {});

        Assertions.assertThat(actIssues.size()).isEqualTo(5);
        Assertions.assertThat(actIssues.get(4).getId()).isEqualTo(4);
    }

    @Test
    public void getIssueByIdSuccessTest() throws Exception{
        Employee man = new Employee(1, "marek", "grechuta", "mgrech", "hash", true);
        Employee emp = new Employee(2, "jacek", "kaczmarski", "jkac", "hash", false);
        Issue issue = new Issue(5, "name", "desc", man, emp, "status");

        when(issueService.findIssueById(5)).thenReturn(issue);
        when(issueService.findIssueById(10)).thenReturn(null);

        RequestBuilder req = MockMvcRequestBuilders.get("/issues/5");
        MvcResult result = mockMvc.perform(req).andReturn();
        String response =  result.getResponse().getContentAsString();

        Issue actIssue = objectMapper.readValue(response, new TypeReference<Issue>() {});

        Assertions.assertThat(actIssue.getId()).isEqualTo(5);
        Assertions.assertThat(actIssue.getStatus()).isEqualTo("status");

    }

    @Test
    public void getIssueByIdFailTest() throws Exception{
        when(issueService.findIssueById(10)).thenReturn(null);

        RequestBuilder req = MockMvcRequestBuilders.get("/issues/10");
        MvcResult result = mockMvc.perform(req).andReturn();
        String response =  result.getResponse().getContentAsString();

        Assertions.assertThat(response).isEmpty();
    }

    @Test
    public void getIssueByContainsTest() throws Exception{
        List<Issue> issues = new LinkedList<>();
        Employee man = new Employee(1, "marek", "grechuta", "mgrech", "hash", true);
        Employee emp = new Employee(2, "jacek", "kaczmarski", "jkac", "hash", false);

        for (int i=0; i<5; i++){
            issues.add(new Issue(i, "name", "desc", man, emp, "status"));
        }

        when(issueService.findIssuesByDescriptionContains("desc")).thenReturn(issues);
        when(issueService.findIssuesByDescriptionContains("adolf")).thenReturn(new ArrayList<>());

        RequestBuilder req = MockMvcRequestBuilders.get("/issues/contains?desc=desc");
        MvcResult result = mockMvc.perform(req).andReturn();
        String response =  result.getResponse().getContentAsString();

        List<Issue> actIssues = objectMapper.readValue(response, new TypeReference<List<Issue>>() {});
        Assertions.assertThat(actIssues).isNotEmpty();
        Assertions.assertThat(actIssues.size()).isEqualTo(5);
        Assertions.assertThat(actIssues.get(2).getId()).isEqualTo(2);
        Assertions.assertThat(actIssues.get(4).getStatus()).isEqualTo("status");

        req = MockMvcRequestBuilders.get("/issues/contains?desc=adolf");
        result = mockMvc.perform(req).andReturn();
        response = result.getResponse().getContentAsString();
        actIssues = objectMapper.readValue(response, new TypeReference<List<Issue>>() {});

        Assertions.assertThat(actIssues).isEmpty();
    }

    @Test
    public void addIssueTest() throws Exception{
        Employee man = new Employee(1, "marek", "grechuta", "mgrech", "hash", true);
        Employee emp = new Employee(2, "jacek", "kaczmarski", "jkac", "hash", false);
        Issue issue = new Issue(5, "name", "desc", man, emp, "status");
        when(issueService.addIssue(any())).thenReturn(issue);

        RequestBuilder req = MockMvcRequestBuilders
                .post("/issues/add")
                .content(objectMapper.writeValueAsString(issue))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON);
        MvcResult result = mockMvc.perform(req).andReturn();
        String response = result.getResponse().getContentAsString();

        Issue actIssue = objectMapper.readValue(response, new TypeReference<Issue>() {});
        Assertions.assertThat(actIssue.getId()).isEqualTo(5);
        Assertions.assertThat(actIssue.getStatus()).isEqualTo("status");
    }

    @Test
    public void deleteIssueTest() throws Exception{
        Employee man = new Employee(1, "marek", "grechuta", "mgrech", "hash", true);
        Employee emp = new Employee(2, "jacek", "kaczmarski", "jkac", "hash", false);
        Issue issue = new Issue(5, "name", "desc", man, emp, "status");

        when(issueService.deleteIssue(5)).thenReturn(true);
        when(issueService.deleteIssue(10)).thenReturn(false);

        RequestBuilder req = MockMvcRequestBuilders.post("/issues/delete/5");
        String response = mockMvc.perform(req).andReturn().getResponse().getContentAsString();

        boolean result = objectMapper.readValue(response, new TypeReference<Boolean>() {});
        Assertions.assertThat(result).isTrue();

        req = MockMvcRequestBuilders.post("/issues/delete/10");
        response = mockMvc.perform(req).andReturn().getResponse().getContentAsString();

        result = objectMapper.readValue(response, new TypeReference<Boolean>() {});
        Assertions.assertThat(result).isFalse();
    }

    @Test
    public void changeStatusTest() throws Exception{
        Employee man = new Employee(1, "marek", "grechuta", "mgrech", "hash", true);
        Employee emp = new Employee(2, "jacek", "kaczmarski", "jkac", "hash", false);
        Issue issue = new Issue(5, "name", "desc", man, emp, "dupa");
        ChangeStatusRequest changeStatusRequest = new ChangeStatusRequest("dupa");
        when(issueService.changeStatus(5, "dupa")).thenReturn(issue);

        RequestBuilder req = MockMvcRequestBuilders
                .post("/issues/update/5")
                .content(objectMapper.writeValueAsString(changeStatusRequest))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON);
        String response = mockMvc.perform(req).andReturn().getResponse().getContentAsString();
        Issue actIssue = objectMapper.readValue(response, new TypeReference<Issue>() {});

        Assertions.assertThat(actIssue.getId()).isEqualTo(5);
        Assertions.assertThat(actIssue.getStatus()).isEqualTo("dupa");

    }
}
