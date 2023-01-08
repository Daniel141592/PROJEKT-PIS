package pis.projekt.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pis.projekt.interfaces.IIssueService;
import pis.projekt.models.Issue;
import pis.projekt.models.requests.ChangeStatusRequest;

import java.util.List;

@RestController
@RequestMapping("/issues")
@CrossOrigin(origins = "http://localhost:5173")
public class IssueController {
    private final IIssueService issueService;

    @Autowired
    public IssueController(IIssueService issueService) {
        this.issueService = issueService;
    }

    @GetMapping("/all")
    public List<Issue> allIssues() {
        return issueService.findAllIssues();
    }

    @GetMapping("{id}")
    public Issue getIssueById(@PathVariable Integer id) {
        return issueService.findIssueById(id);
    }

    @GetMapping("/contains")
    public List<Issue> getIssuesContains(@RequestParam("desc") String desc) {
        return issueService.findIssuesByDescriptionContains(desc);
    }

    @PostMapping("/add")
    public Issue addIssue(@RequestBody Issue issue) {
        return issueService.addIssue(issue);
    }

    @PostMapping("delete/{id}")
    public boolean deleteIssue(@PathVariable Integer id) {
        return issueService.deleteIssue(id);
    }

    @PostMapping("/update/{id}")
    public Issue changeStatus(@RequestBody ChangeStatusRequest status, @PathVariable Integer id) {
        return issueService.changeStatus(id, status.getStatus());
    }
}
