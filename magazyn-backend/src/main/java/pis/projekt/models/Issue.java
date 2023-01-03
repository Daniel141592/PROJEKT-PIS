package pis.projekt.models;

import jakarta.persistence.*;

@Entity
@Table(name = "zlecenia")
public class Issue {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
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

    Issue(int newId, String newName, String newDescription, Employee newManager, Employee newEmployee, String newStatus){
        this.id = newId;
        this.name = newName;
        this.description = newDescription;
        this.issuingManager = newManager;
        this.issuedEmployee = newEmployee;
        this.status = newStatus;
        // maybe add sending to bd here
    }

    public Issue() {

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

    public Employee getIssuingManager() {
        return issuingManager;
    }

    public Employee getIssuedEmployee() {
        return issuedEmployee;
    }

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

    /*public void changeStatus(String newStatus, Employee statusChanger){
        if(statusChanger == issuingManager || statusChanger == issuedEmployee){
            status = newStatus;
        }
    }*/
}
