package pis.projekt.models.requests;

import jakarta.validation.constraints.NotNull;

public class LoginRequest {
    @NotNull
    private String username;
    @NotNull
    private String password;

    public LoginRequest(String username, String password){
        this.username = username;
        this.password = password;
    }

    public LoginRequest(String value, boolean isUsername){
        if (!isUsername){
            this.password = value;
        }
        this.username = value;
    }

    public LoginRequest(){

    }


    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }
}
