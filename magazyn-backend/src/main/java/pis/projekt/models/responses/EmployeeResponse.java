package pis.projekt.models.responses;


import pis.projekt.models.Employee;

public class EmployeeResponse {
    private int id;
    private String name;
    private String surname;
    private String login;
    private boolean isManager;

    public EmployeeResponse(Employee employee) {
        this.id = employee.getId();
        this.name = employee.getName();
        this.surname = employee.getSurname();
        this.login = employee.getLogin();
        this.isManager = employee.getIsManager();
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public String getLogin() {
        return login;
    }

    public boolean isManager() {
        return isManager;
    }
}
