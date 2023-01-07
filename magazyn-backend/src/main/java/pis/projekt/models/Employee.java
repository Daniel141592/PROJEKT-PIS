package pis.projekt.models;

import jakarta.persistence.*;

@Entity
@Table(name = "pracownicy")
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private int id;
    @Column(name = "first_name", nullable = false)
    private String name;
    @Column(name = "last_name", nullable = false)
    private String surname;
    @Column(name = "login", nullable = false)
    private String login;
    @Column(name = "password_hash", nullable = false)
    private String passwordHash;
    @Column(name = "is_manager", nullable = false)
    private boolean isManager;

    public Employee(int newId, String newName, String newSurname, String newLogin, String newPasswordHash, boolean isManager){
        this.id = newId;
        this.name = newName;
        this.surname = newSurname;
        this.login = newLogin;
        this.passwordHash = newPasswordHash;
        this.isManager = isManager;
    }

    public Employee() {

    }

    public Employee(Integer id) {
        this.id = id;
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
