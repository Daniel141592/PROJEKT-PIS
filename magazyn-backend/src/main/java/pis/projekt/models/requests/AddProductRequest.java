package pis.projekt.models.requests;

import jakarta.validation.constraints.NotNull;

public class AddProductRequest {
    @NotNull
    private Integer sectionId;
    @NotNull
    private  Integer amount;

    public AddProductRequest(Integer sectionId, Integer amount) {
        this.sectionId = sectionId;
        this.amount = amount;
    }

    public Integer getSectionId() {
        return sectionId;
    }

    public Integer getAmount() {
        return amount;
    }
}
