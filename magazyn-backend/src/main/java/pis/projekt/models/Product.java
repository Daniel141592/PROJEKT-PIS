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

    public static boolean isSame(Product product1, Product product2){
        boolean sameName = product1.getName() == product2.name;
        boolean sameDimensions = Pair.isSame(product1.getDimensions(), product2.dimensions);
        boolean sameStackSize = product1.getStackSize() == product2.stackSize;
        return sameName && sameDimensions && sameStackSize;
    }

    public String getName() {
        return name;
    }

    public Pair getDimensions() { return dimensions; }

    public int getStackSize() { return stackSize; }

}
