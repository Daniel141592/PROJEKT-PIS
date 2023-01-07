package pis.projekt.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pis.projekt.models.Issue;

import java.util.List;

public interface IIssueRepository extends JpaRepository<Issue, Integer> {
    Issue findIssueById(Integer issueId);

    List<Issue> findIssuesByIssuingManager_Id(Integer managerId);

    List<Issue> findIssuesByIssuedEmployee_Id(Integer employeeId);

    List<Issue> findIssuesByDescriptionContains(String desc);
}
