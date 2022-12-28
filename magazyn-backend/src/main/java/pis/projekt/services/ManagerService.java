package pis.projekt.services;

import pis.projekt.models.Employee;
import pis.projekt.models.Issue;

public class ManagerService extends EmployeeService {
    ManagerService(int newId, String newName, String newSurname, String newLogin, String newPassword) {
        super(newId, newName, newSurname, newLogin, newPassword);
    }



    public void assignIssue(Employee assignedEmployee, Issue issue){

    }

    @Override
    public boolean isManager() {
        return true;
    }
}
