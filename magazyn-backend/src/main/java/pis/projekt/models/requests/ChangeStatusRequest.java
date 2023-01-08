package pis.projekt.models.requests;

import jakarta.validation.constraints.NotNull;

public class ChangeStatusRequest {
    @NotNull
    private String status;

    public ChangeStatusRequest() { }

    public ChangeStatusRequest(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }
}
