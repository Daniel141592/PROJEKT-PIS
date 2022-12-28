package pis.projekt.models;

import pis.projekt.services.MagazineService;
import pis.projekt.utils.Pair;

public class Section {
    private int id;
    private Pair[] cords;
    private Magazine magazine;
    private Product product;
    private String name;
    private Integer amount;

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Pair[] getCords() {
        return cords;
    }

    public Magazine getMagazine() {
        return magazine;
    }

    public Product getProduct() {
        return product;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCords(Pair[] cords) {
        this.cords = cords;
    }

    public void setMagazine(Magazine magazine) {
        this.magazine = magazine;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }
}
