package pis.projekt.models;

import pis.projekt.utils.Pair;

public class Product {
    private String name;
    private Pair dimensions;
    private int stackSize; // this name asks for God's intervention

    public Product()
    {
        name = "";
        dimensions = new Pair();
        stackSize = 0;
    }

    public Product(String newName, int newLength, int newWidth, int newStackSize)
    {
        name = newName;
        dimensions = new Pair(newLength, newWidth);
        stackSize = newStackSize;
    }

    public String getName() {
        return name;
    }

    public Pair getDimensions() { return dimensions; }

    public int getStackSize() { return stackSize; }

}
