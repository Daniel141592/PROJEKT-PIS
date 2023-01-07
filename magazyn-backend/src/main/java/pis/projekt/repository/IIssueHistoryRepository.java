package pis.projekt.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pis.projekt.models.Issue;
import pis.projekt.models.IssueHistory;

import java.sql.Timestamp;
import java.util.List;

public interface IIssueHistoryRepository extends JpaRepository<IssueHistory, Integer> {
    IssueHistory findIssueHistoryById(Integer issueHistoryId);

    List<IssueHistory> findIssueHistoriesByIssuingManager_Id(Integer managerId);

    List<IssueHistory> findIssueHistoriesByIssuedEmployee_Id(Integer employeeId);

    List<IssueHistory> findIssueHistoriesByDescriptionContains(String desc);

    List<IssueHistory> findIssueHistoriesByModifyDate(Timestamp modifyDate);
}
