package pis.projekt.requests;

import jakarta.validation.constraints.NotNull;

public class RegisterRequest extends LoginRequest {
    @NotNull
    public String confirmPassword;
    @NotNull
    public String email;

    public RegisterRequest(String username, String password, String confirmPassword, String email){
        super(username, password);
        this.confirmPassword = confirmPassword;
        this.email = email;
    }
}
