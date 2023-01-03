package pis.projekt.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pis.projekt.interfaces.IIssueService;
import pis.projekt.models.Issue;
import pis.projekt.repository.IIssueRepository;

import java.util.List;

@Service
public class IssueService implements IIssueService {
    @Autowired
    IIssueRepository issueRepository;

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
}
