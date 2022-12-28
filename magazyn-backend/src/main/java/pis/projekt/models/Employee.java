package pis.projekt.models;

public class Employee {
    private int id;
    private String name;
    private String surname;
    private String login;
    private String password;
    private boolean isManager;

    Employee(int newId, String newName, String newSurname, String newLogin, String newPassword, boolean isManager){
        this.id = newId;
        this.name = newName;
        this.surname = newSurname;
        this.login = newLogin;
        this.password = newPassword;
        this.isManager = isManager;
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

    public String getPassword() {
        return password;
    }

    public boolean getIsManager(){
        return isManager;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setIsManager(boolean isManager) {
        this.isManager = isManager;
    }
}
