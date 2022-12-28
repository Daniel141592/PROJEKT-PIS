package pis.projekt.models;

public class Employee {
    private int id;
    private String name;
    private String surname;
    private String login;
    private String passwordHash;
    private boolean isManager;

    Employee(int newId, String newName, String newSurname, String newLogin, String newPasswordHash, boolean isManager){
        this.id = newId;
        this.name = newName;
        this.surname = newSurname;
        this.login = newLogin;
        this.passwordHash = newPasswordHash;
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

    public String getPasswordHash() {
        return passwordHash;
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

    public void setPasswordHash(String passwordHash) {
        this.passwordHash = passwordHash;
    }

    public void setIsManager(boolean isManager) {
        this.isManager = isManager;
    }
}
