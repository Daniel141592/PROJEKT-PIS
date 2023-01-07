package pis.projekt.models;


import jakarta.persistence.*;
import pis.projekt.models.responses.EmployeeResponse;

import java.sql.Timestamp;
import java.time.Clock;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

@Entity
@Table(name = "historia_zlecen")
public class IssueHistory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private int id;
    @Column(name = "name", nullable = false)
    private String name;
    @Column(name = "description", nullable = false)
    private String description;
    @ManyToOne
    @JoinColumn(name = "kier_id", nullable = false)
    private Employee issuingManager;
    @ManyToOne
    @JoinColumn(name = "prac_id", nullable = false)
    private Employee issuedEmployee;
    @Column(name = "status", nullable = false)
    private String status;
    @Column(name = "modify_date", nullable = false)
    private Timestamp modifyDate;


    public IssueHistory(int newId, String newName, String newDescription, Employee newManager, Employee newEmployee, String newStatus, Timestamp modifyData){
        this.id = newId;
        this.name = newName;
        this.description = newDescription;
        this.issuingManager = newManager;
        this.issuedEmployee = newEmployee;
        this.status = newStatus;
        this.modifyDate = Timestamp.from(Instant.now(Clock.system(ZoneId.of("Europe/Warsaw"))));
    }

    public IssueHistory(Issue issue){
        this.id = issue.getId();
        this.name = issue.getName();
        this.description = issue.getDescription();
        this.issuingManager = new Employee(issue.getIssuingManager().getId());
        this.issuedEmployee = new Employee(issue.getIssuedEmployee().getId());
        this.status = issue.getStatus();
        this.modifyDate = Timestamp.from(Instant.now(Clock.system(ZoneId.of("Europe/Warsaw"))));
        System.out.println(modifyDate);
    }

    public IssueHistory() {

    }

    public String getName() {
        return name;
    }

    public int getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }

    public String getStatus() {
        return status;
    }

    public EmployeeResponse getIssuingManager() {
        return new EmployeeResponse(issuingManager);
    }

    public EmployeeResponse getIssuedEmployee() {
        return new EmployeeResponse(issuedEmployee);
    }

    public Timestamp getModifyDate() { return modifyDate; }

    public void setName(String name) {
        this.name = name;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setIssuingManager(Employee issuingManager) {
        this.issuingManager = issuingManager;
    }

    public void setIssuedEmployee(Employee issuedEmployee) {
        this.issuedEmployee = issuedEmployee;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setModifyDate(Timestamp modifyDate) { this.modifyDate = modifyDate; }
}
