package pis.projekt;

public class Product {
    private String name;
    private int length;
    private int width;
    private int stackability;

    Product(String newName, int newLength, int newWidth, int newStackability)
    {
        name = newName;
        length = newLength;
        width = newWidth;
        stackability = newStackability;
    }

    public String getName() {
        return name;
    }

    public int getLength() {
        return length;
    }

    public int getWidth() {
        return width;
    }

    public int getStackability() {
        return stackability;
    }


}
