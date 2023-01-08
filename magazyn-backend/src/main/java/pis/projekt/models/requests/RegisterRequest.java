package pis.projekt.models.requests;

import jakarta.validation.constraints.NotNull;

public class RegisterRequest extends LoginRequest {
    @NotNull
    private String confirmPassword;
    @NotNull
    private String email;

    public RegisterRequest(String username, String password, String confirmPassword, String email){
        super(username, password);
        this.confirmPassword = confirmPassword;
        this.email = email;
    }

    public RegisterRequest(){
        super();
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public String getEmail() {
        return email;
    }
}
