package pis.projekt.services;

import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import pis.projekt.models.Employee;
import pis.projekt.models.Issue;
import pis.projekt.repository.IIssueHistoryRepository;
import pis.projekt.repository.IIssueRepository;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class IssueServiceTest {

    @Mock
    IIssueRepository IR;

    @Mock
    IIssueHistoryRepository IHR;

    @InjectMocks
    IssueService IService;

    @Test
    public void findAllIssuesTest(){
        List<Issue> issues = new ArrayList<>();
        issues.add(new Issue(1, "name1", "desc1", null, null, null));
        issues.add(new Issue(2, "name2", "desc2", null, null, null));
        issues.add(new Issue(3, "name3", "desc3", null, null, null));

        when(IR.findAll()).thenReturn(issues);
        Assertions.assertThat(IService.findAllIssues()).isSameAs(issues);
        Assertions.assertThat(IService.findAllIssues().size()).isEqualTo(3);

    }

    @Test
    public void findIssueByIdTest(){
        List<Issue> issues = new ArrayList<>();
        Issue iss1 = new Issue(1, "name1", "desc1", null, null, null);
        issues.add(iss1);
        issues.add(new Issue(2, "name2", "desc2", null, null, null));
        issues.add(new Issue(3, "name3", "desc3", null, null, null));

        when(IR.findIssueById(1)).thenReturn(iss1);
        Assertions.assertThat(IService.findIssueById(1)).isSameAs(iss1);
        Assertions.assertThat(IService.findIssueById(1).getName()).isEqualTo("name1");
        Assertions.assertThat(IService.findIssueById(4)).isNull();
    }

    @Test
    public void findIssuesByIssuingManager_IdTest(){
        List<Issue> issues = new ArrayList<>();
        Issue iss1 = new Issue(1, "name1", "desc1", null, null, null);
        issues.add(iss1);
        issues.add(new Issue(2, "name2", "desc2", null, null, null));

        when(IR.findIssuesByIssuingManager_Id(1)).thenReturn(issues);
        Assertions.assertThat(IService.findIssuesByIssuingManager_Id(1)).isSameAs(issues);
        Assertions.assertThat(IService.findIssuesByIssuingManager_Id(1).get(0).getName()).isEqualTo("name1");
        Assertions.assertThat(IService.findIssuesByIssuingManager_Id(3)).isEmpty();
    }

    @Test
    public void findIssuesByIssuedEmployee_IdTest(){
        List<Issue> issues = new ArrayList<>();
        Issue iss1 = new Issue(1, "name1", "desc1", null, null, null);
        issues.add(iss1);
        issues.add(new Issue(2, "name2", "desc2", null, null, null));

        when(IR.findIssuesByIssuedEmployee_Id(68)).thenReturn(issues);
        Assertions.assertThat(IService.findIssuesByIssuedEmployee_Id(68)).isSameAs(issues);
        Assertions.assertThat(IService.findIssuesByIssuedEmployee_Id(68).get(1).getDescription()).isEqualTo("desc2");
        Assertions.assertThat(IService.findIssuesByIssuedEmployee_Id(27)).isEmpty();
    }

    @Test
    public void findIssuesByDescriptionContainsTest(){
        List<Issue> issues = new ArrayList<>();
        Issue iss1 = new Issue(1, "name1", "desc1 totallynottest", null, null, null);
        Issue iss2 = new Issue(2, "name2", "desc2!#/??&SQLINJECTION", null, null, null);
        Issue iss3 = new Issue(3, "name3", "desc3 MEDEIS", null, null, null);
        issues.add(iss1);
        issues.add(iss2);
        issues.add(iss3);

        when(IR.findIssuesByDescriptionContains("desc")).thenReturn(issues);
        Assertions.assertThat(IService.findIssuesByDescriptionContains("desc")).isSameAs(issues);
        Assertions.assertThat(IService.findIssuesByDescriptionContains("desc").size()).isEqualTo(3);
        Assertions.assertThat(IService.findIssuesByDescriptionContains("rura wydechowa")).isEmpty();
    }

    @Test
    public void addIssueTest(){
        Employee emp = new Employee(1, "name", "surname", "login", "hash", false);
        Employee man = new Employee(2, "name2", "surname2", "login2", "hash2", true);
        Issue iss1 = new Issue(1, "name1", "desc1 totallynottest", man, emp, null);

        when(IR.save(iss1)).thenReturn(iss1);
        Assertions.assertThat(IService.addIssue(iss1)).isSameAs(iss1);
    }

    @Test
    public void deleteIssueSuccessfulTest(){
        when(IR.existsById(1)).thenReturn(true);
        Assertions.assertThat(IService.deleteIssue(1)).isTrue();
    }

    @Test
    public void deleteIssueFailedTest(){
        when(IR.existsById(1)).thenReturn(false);
        Assertions.assertThat(IService.deleteIssue(1)).isFalse();
    }

    @Test
    public void changeStatusTest(){
        Employee emp = new Employee(1, "name", "surname", "login", "hash", false);
        Employee man = new Employee(2, "name2", "surname2", "login2", "hash2", true);
        Issue iss1 = new Issue(1, "name1", "desc1 totallynottest", man, emp, null);
        String status = "statusik";
        when(IR.findIssueById(1)).thenReturn(iss1);
        when(IR.save(iss1)).thenReturn(iss1);

        Assertions.assertThat(IService.changeStatus(1, status)).isSameAs(iss1);
    }


}
