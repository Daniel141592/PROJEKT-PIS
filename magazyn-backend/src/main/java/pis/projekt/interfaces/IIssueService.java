package pis.projekt.interfaces;

import org.springframework.data.relational.core.sql.In;
import pis.projekt.models.Issue;

import java.util.List;

public interface IIssueService {
    Issue findIssueById(Integer issueId);

    List<Issue> findIssuesByIssuingManager_Id(Integer managerId);

    List<Issue> findIssuesByIssuedEmployee_Id(Integer employeeId);

    List<Issue> findIssuesByDescriptionContains(String desc);

    List<Issue> findAllIssues();

    Issue addIssue(Issue issue);

    boolean deleteIssue(Integer issueId);

    Issue changeStatus(Integer issueId, String status);

    List<Issue> getIssuesOfCurrentlyLoggedEmp();
}
