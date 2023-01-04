package pis.projekt.requests;

import jakarta.validation.constraints.NotNull;

public class AddProductRequest {
    @NotNull
    public Integer sectionId;
    @NotNull
    public  Integer amount;

    public AddProductRequest(Integer sectionId, Integer amount) {
        this.sectionId = sectionId;
        this.amount = amount;
    }
}
