package pis.projekt.models;

import pis.projekt.utils.Pair;

public class Magazine {
    private int id;
    private String name;
    private Pair dimensions;

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDimensions(Pair dimensions) {
        this.dimensions = dimensions;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Pair getDimensions() {
        return dimensions;
    }
}
