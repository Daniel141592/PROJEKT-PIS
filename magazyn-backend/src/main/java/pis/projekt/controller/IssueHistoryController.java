package pis.projekt.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pis.projekt.interfaces.IIssueHistoryService;
import pis.projekt.interfaces.IIssueService;
import pis.projekt.models.IssueHistory;

import java.util.List;

@RestController
@RequestMapping("/issueHistories")
@CrossOrigin(origins = "http://localhost:5173")
public class IssueHistoryController {
    private final IIssueHistoryService issueHistoryService;

    @Autowired
    public IssueHistoryController(IIssueHistoryService issueHistoryService) {
        this.issueHistoryService = issueHistoryService;
    }

    @GetMapping("/all")
    public List<IssueHistory> allIssues() {
        return issueHistoryService.findAllIssueHistories();
    }

    @GetMapping("{id}")
    public IssueHistory getIssueById(@PathVariable Integer id) {
        return issueHistoryService.findIssueHistoryById(id);
    }

    @GetMapping("/contains")
    public List<IssueHistory> getIssuesContains(@RequestParam("desc") String desc) {
        return issueHistoryService.findIssueHistoriesByDescriptionContains(desc);
    }

    @PostMapping("/add")
    public IssueHistory addIssue(@RequestBody IssueHistory issueHistory) {
        return issueHistoryService.addIssueHistory(issueHistory);
    }

    @PostMapping("delete/{id}")
    public boolean deleteIssueHistory(@PathVariable Integer id) {
        return issueHistoryService.deleteIssueHistory(id);
    }
}
