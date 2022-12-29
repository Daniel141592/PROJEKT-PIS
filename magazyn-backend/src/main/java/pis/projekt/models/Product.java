package pis.projekt.models;

import jakarta.persistence.*;
import pis.projekt.utils.Pair;

import java.util.Objects;

@Entity
@Table(name = "produkty")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    int id;
    @Column(name = "name", nullable = false)
    private String name;
    @Column(name = "length", nullable = false)
    private int length;
    @Column(name = "width", nullable = false)
    private int width;
    @Column(name = "stack_size", nullable = false)
    private int stackSize;

    public Product()
    {
        name = "";
        width = 0;
        length = 0;
        stackSize = 0;
    }

    public Product(String newName, int newLength, int newWidth, int newStackSize)
    {
        name = newName;
        width = newWidth;
        length = newLength;
        stackSize = newStackSize;
    }

    public static boolean isSame(Product product1, Product product2){
        boolean sameName = Objects.equals(product1.getName(), product2.name);
        boolean sameLength = product1.length == product2.length;
        boolean sameWidth = product1.width == product2.width;
        boolean sameStackSize = product1.getStackSize() == product2.stackSize;
        return sameName && sameLength && sameWidth && sameStackSize;
    }

    public String getName() {
        return name;
    }

    public Pair getDimensions() { return new Pair(width, length); }

    public int getStackSize() { return stackSize; }

    public void setName(String name) {
        this.name = name;
    }

    public void setStackSize(int stackSize) {
        this.stackSize = stackSize;
    }

    public int getId() {
        return id;
    }
}
