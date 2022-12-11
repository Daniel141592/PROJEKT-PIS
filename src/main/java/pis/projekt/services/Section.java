package pis.projekt.services;
import pis.projekt.models.Product;
import pis.projekt.utils.Pair;

import java.lang.Math;

public class Section {
    // lets try to get them in some sensible order
    // (upper left as first one, down left second, down right third...)
    private Pair[] points;
    private Magazine magazine;
    private Product product;
    private String name;
    private int capacity;


    Section(Pair[] newPoints, Magazine newMagazine, Product newProduct, String newName){
        points = newPoints;
        magazine = newMagazine;
        product = newProduct;
        name = newName;

    }

    public Pair[] getPoints() {
        return points;
    }

    public Magazine getMagazine() {
        return magazine;
    }

    public Product getProduct() {
        return product;
    }

    public int getCapacity() { return capacity; }

    public String getName() {
        return name;
    }

    public void forceSetMagazine(Magazine magazine) {
        this.magazine = magazine;
    }

    // to be finished when database comes
    //public void setMagazine(Magazine magazine) { if(!magazine.checkCollision(this, ))}

    public void setProduct(Product product) {
        this.product = product;
    }

    public void addProduct() {if(capacity < this.calcMaxCapacity()){ this.capacity += 1;}}

    public int calcMaxCapacity() {
        int length = points[1].getValue() - points[0].getValue();
        int width = points[3].getKey() - points[0].getKey();
        return (length / product.getLength() ) * (width / product.getWidth() ) * product.getStackability();
    }

    boolean containsPoint(Pair point){
        boolean cond1 = point.getKey() > points[0].getKey();
        boolean cond2 = point.getKey() < points[2].getKey();
        boolean cond3 = point.getValue() < points[1].getValue();
        boolean cond4 = point.getValue() > points[3].getValue();
        if(cond1 && cond2 && cond3 && cond4){
            return true;
        }
        return false;
    }
    //calculates area, points must be ordered clockwise or counterclockwise
    public double calcArea(){
        int psum = 0;
        int nsum = 0;
        int sindex = 0;

        for(int i = 0; i < getPoints().length; i++){
            sindex = (i+1) % getPoints().length;
            psum += getPoints()[i].getKey() * getPoints()[sindex].getValue();
            nsum += getPoints()[sindex].getKey() * getPoints()[i].getValue();

        }

        return Math.abs(0.5*(psum - nsum));


    }

}
