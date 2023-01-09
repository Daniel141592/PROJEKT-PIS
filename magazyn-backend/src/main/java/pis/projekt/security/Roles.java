package pis.projekt.security;

public enum Roles {
    MANAGER("manager"),
    USER("user");

    Roles(String name) {
        this.name = name;
    }
    private final String name;

    @Override
    public String toString() {
        return name;
    }
}
