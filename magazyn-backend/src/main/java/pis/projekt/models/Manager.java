package pis.projekt.models;

public class Manager extends Employee{
    Manager(int newId, String newName, String newSurname, String newLogin, String newPassword) {
        super(newId, newName, newSurname, newLogin, newPassword);
    }



    public void assignIssue(Employee assignedEmployee, Issue issue){

    }

    @Override
    public boolean isManager() {
        return true;
    }
}
