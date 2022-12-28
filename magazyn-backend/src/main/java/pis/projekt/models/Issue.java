package pis.projekt.models;

public class Issue {
    private int id;
    private String name;
    private String description;
    private Manager issuingManager;
    private Employee issuedEmployee;
    private String status;

    Issue(int newId, String newName, String newDescription, Manager newManager, Employee newEmployee, String newStatus){
        this.id = newId;
        this.name = newName;
        this.description = newDescription;
        this.issuingManager = newManager;
        this.issuedEmployee = newEmployee;
        this.status = newStatus;
        // maybe add sending to bd here
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

    public Manager getIssuingManager() {
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

    public void setIssuingManager(Manager issuingManager) {
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
