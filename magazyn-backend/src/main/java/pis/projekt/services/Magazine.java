package pis.projekt.services;

import pis.projekt.models.Product;
import pis.projekt.utils.Pair;

import java.util.Vector;

public class Magazine {
    private int id;
    private String name;
    private int length;
    private int width;
    private Vector<Section> magazineBoundSections;

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

    public int getAmountOfSections(){return magazineBoundSections.size(); }

    // return true if added, else return false
    public boolean addSection(Section newSection){
        if(!checkCollision(newSection)){
            magazineBoundSections.add(newSection);
            return true;
        }
        return false;
    }

    // not sure how we will handle magazine bound sections now
    // true = collision, false = no collision
    boolean checkCollision(Section newSection){
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

    double calcEmptySpace(boolean inPrecent){

        double area = 0;
        for(Section sec: magazineBoundSections){
            area += sec.calcArea();
        }
        if(inPrecent){
            area = area / (this.length * this.width);
        }
        return area;
    }

    int getProductAmount(Product product){
        int amount = 0;
        for(Section sec: magazineBoundSections){
            if(sec.getProduct() == product){amount += sec.getCapacity();}
        }
        return amount;
    }



}
