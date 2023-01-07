package pis.projekt.interfaces;

import pis.projekt.models.Issue;
import pis.projekt.models.IssueHistory;

import java.sql.Timestamp;
import java.util.List;

public interface IIssueHistoryService {
    IssueHistory findIssueHistoryById(Integer issueHistoryId);

    List<IssueHistory> findIssueHistoriesByIssuingManager_Id(Integer managerId);

    List<IssueHistory> findIssueHistoriesByIssuedEmployee_Id(Integer employeeId);

    List<IssueHistory> findIssueHistoriesByDescriptionContains(String desc);

    List<IssueHistory> findIssueHistoriesByModifyDate(Timestamp modifyDate);

    List<IssueHistory> findAllIssueHistories();

    IssueHistory addIssueHistory(IssueHistory issueHistory);

    boolean deleteIssueHistory(Integer issueHistoryId);

}
