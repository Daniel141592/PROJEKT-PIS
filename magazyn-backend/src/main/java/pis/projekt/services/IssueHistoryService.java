package pis.projekt.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pis.projekt.models.IssueHistory;
import pis.projekt.repository.IIssueHistoryRepository;
import pis.projekt.interfaces.IIssueHistoryService;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class IssueHistoryService implements IIssueHistoryService {
    @Autowired
    IIssueHistoryRepository issueHistoryRepository;

    @Override
    public List<IssueHistory> findAllIssueHistories() {
        return issueHistoryRepository.findAll();
    }

    @Override
    public IssueHistory findIssueHistoryById(Integer issueHistoryId) {
        return issueHistoryRepository.findIssueHistoryById(issueHistoryId);
    }

    @Override
    public List<IssueHistory> findIssueHistoriesByIssuingManager_Id(Integer managerId) {
        return issueHistoryRepository.findIssueHistoriesByIssuingManager_Id(managerId);
    }

    @Override
    public List<IssueHistory> findIssueHistoriesByIssuedEmployee_Id(Integer employeeId) {
        return issueHistoryRepository.findIssueHistoriesByIssuedEmployee_Id(employeeId);
    }

    @Override
    public List<IssueHistory> findIssueHistoriesByDescriptionContains(String desc) {
        return issueHistoryRepository.findIssueHistoriesByDescriptionContains(desc);
    }

    @Override
    public List<IssueHistory> findIssueHistoriesByModifyDate(LocalDateTime modifyDate) {
        return issueHistoryRepository.findIssueHistoriesByModifyDate(modifyDate);
    }


    @Override
    public IssueHistory addIssueHistory(IssueHistory issueHistory) {
        return issueHistoryRepository.save(issueHistory);
    }


}
