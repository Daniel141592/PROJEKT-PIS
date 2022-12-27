package pis.projekt.services;

import pis.projekt.models.Product;
import pis.projekt.utils.Pair;

import java.util.Vector;

public class Magazine {
    private int id;
    private String name;
    private int length;
    private int width;

    public Magazine(){
        id = 0;
        name = "";
        length = 0;
        width = 0;
    }

    public Magazine(int newId, String newName, int newLength, int newWidth){
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

    // not sure how we will handle magazine bound sections now
    // true = collision, false = no collision
    boolean checkCollision(Section newSection, Vector<Section> magazineBoundSections){
        boolean isSame;
        for(Section sec: magazineBoundSections){
            isSame = true;
            for(Pair newSecPoint: newSection.getCords()){
                if(sec.containsPoint(newSecPoint)){return true;}
            }
            for(Pair oldSecPoint: sec.getCords()){
                if(newSection.containsPoint(oldSecPoint)){return true;}
            }
            for(int i=0; i<4; i++){
                if(sec.getCords() != newSection.getCords()){
                    isSame = false;
                }
            }
            if(isSame){return true;}
        }
        return false;
    }

    double calcEmptySpace(Vector<Section> magazineBoundSections, boolean inPrecent){

        double area = 0;
        for(Section sec: magazineBoundSections){
            area += sec.calcArea();
        }
        if(inPrecent){
            area = area / (this.length * this.width);
        }
        return area;
    }

    int getProductAmount(Vector<Section> magazineBoundSections, Product product){
        int amount = 0;
        for(Section sec: magazineBoundSections){
            if(sec.getProduct() == product){amount += sec.getCapacity();}
        }
        return amount;
    }



}
