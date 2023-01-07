package pis.projekt.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pis.projekt.interfaces.IIssueService;
import pis.projekt.models.Issue;
import pis.projekt.models.IssueHistory;
import pis.projekt.repository.IIssueHistoryRepository;
import pis.projekt.repository.IIssueRepository;

import java.util.List;

@Service
public class IssueService implements IIssueService {
    @Autowired
    IIssueRepository issueRepository;

    @Autowired
    IIssueHistoryRepository issueHistoryRepository;

    @Override
    public List<Issue> findAllIssues() {
        return issueRepository.findAll();
    }

    @Override
    public Issue findIssueById(Integer issueId) {
        return issueRepository.findIssueById(issueId);
    }

    @Override
    public List<Issue> findIssuesByIssuingManager_Id(Integer managerId) {
        return issueRepository.findIssuesByIssuingManager_Id(managerId);
    }

    @Override
    public List<Issue> findIssuesByIssuedEmployee_Id(Integer employeeId) {
        return issueRepository.findIssuesByIssuedEmployee_Id(employeeId);
    }

    @Override
    public List<Issue> findIssuesByDescriptionContains(String desc) {
        return issueRepository.findIssuesByDescriptionContains(desc);
    }

    @Override
    public Issue addIssue(Issue issue) {
        issueHistoryRepository.save(new IssueHistory(issue));
        return issueRepository.save(issue);
    }

    @Override
    public boolean deleteIssue(Integer issueId){
        if(!issueRepository.existsById(issueId))
            return false;
        issueRepository.deleteById(issueId);
        return true;
    }

    @Override
    public Issue changeStatus(Integer issueId, String status) {
        Issue issue = issueRepository.findIssueById(issueId);
        issue.setStatus(status);
        issueHistoryRepository.save(new IssueHistory(issue));
        return issueRepository.save(issue);
    }
}
