package pis.projekt.services;

import pis.projekt.models.Product;
import pis.projekt.utils.Pair;

import java.util.Vector;

public class Magazine {
    private int id;
    private String name;
    private Pair dimensions;
    private Vector<Section> sections;

    public Magazine(){
        id = 0;
        name = "";
        dimensions = new Pair();
        sections = new Vector<Section>();
    }

    public Magazine(int newId, String newName, int newLength, int newWidth){
        id = newId;
        name = newName;
        dimensions = new Pair(newLength, newWidth);
        sections = new Vector<Section>();
    }

    public Magazine(int id, String name, int length, int width, Section section){
        this(id, name, length, width);
        this.sections.add(section);
    }

    public Magazine(int id, String name, int length, int width, Vector<Section> sections){
        this(id, name, length, width);
        this.sections = sections;
    }

    public String getName() {
        return name;
    }

    public void addSection(Section section){
        this.sections.add(section);
    }

    public Vector<Section> getSections(){
        return sections;
    }

    public int getId() {
        return id;
    }

    public Pair getDimensions() { return dimensions;}

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
            area = area / (this.dimensions.first * this.dimensions.second);
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
