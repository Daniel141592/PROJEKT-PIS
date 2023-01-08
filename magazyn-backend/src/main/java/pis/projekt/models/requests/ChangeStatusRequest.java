package pis.projekt.models.requests;

import jakarta.validation.constraints.NotNull;

public class ChangeStatusRequest {
    @NotNull
    String status;

    public ChangeStatusRequest(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }
}
