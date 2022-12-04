package pis.projekt;

import java.util.Vector;

public class Magazine {
    private int id;
    private String name;
    private int length;
    private int width;

    Magazine(int newId, String newName, int newLength, int newWidth){
        id = newId;
        name = newName;
        length = newLength;
        width = newWidth;
    }

    public String getName() {
        return name;
    }

    public int getId() {
        return id;
    }

    public int getLength() {
        return length;
    }

    public int getWidth() {
        return width;
    }

    boolean checkCollision(Section newSection, Vector<Section> magazineBoundSections){}

    int calcEmptySpace(Vector<Section> magazineBoundSection, boolean inPrecent){}

    int getProductAmount(Product product){}


}
