package pis.projekt;
import javafx.util.Pair;

public class Section {
    private Pair<Integer, Integer> startingPoint;
    private int length;
    private int width;
    private Magazine magazine;
    private Product product;
    private String name;

    public Pair<Integer, Integer> getStartingPoint() {
        return startingPoint;
    }

    public int getLength() {
        return length;
    }

    public int getWidth() {
        return width;
    }

    public Magazine getMagazine() {
        return magazine;
    }

    public Product getProduct() {
        return product;
    }

    public String getName() {
        return name;
    }

    public void setMagazine(Magazine magazine) {
        this.magazine = magazine;
    }

    public void setProduct(Product product) {
        this.product = product;
    }


}
