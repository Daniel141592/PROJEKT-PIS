package pis.projekt.services;

import pis.projekt.models.Product;
import pis.projekt.utils.Pair;

import java.util.Vector;

import static java.lang.Math.abs;
import static java.lang.Math.round;

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

    public Vector<Section> getSections(){
        return sections;
    }

    public int getId() {
        return id;
    }

    public Pair getDimensions() { return dimensions;}

    public int getSectionsAmount(){ return sections.size(); }

    boolean checkCollision(Section newSection){
        boolean isSame;
        for(Section sec: sections){
            isSame = true;
            for(Pair newSecPoint: newSection.getCords()){
                if(sec.containsPoint(newSecPoint)){ return true; }
            }
            for(Pair oldSecPoint: sec.getCords()){
                if(newSection.containsPoint(oldSecPoint)){ return true; }
            }
            for(int i=0; i<4; i++){
                if(sec.getCords() != newSection.getCords()){
                    isSame = false;
                }
            }
            if(isSame){ return true; }
        }
        return false;
    }

    // return true if added, else return false
    public boolean addSection(Section newSection){
        if(!checkCollision(newSection)){
            sections.add(newSection);
            return true;
        }
        return false;
    }

    public double calcSpace(){
        double absSpace = dimensions.first * dimensions.second;
        return absSpace;
    }

    public double calcEmptySpace(boolean inPercent){

        double absArea = calcSpace();
        double area = absArea;
        for(Section sec: sections){
            area -= sec.calcArea();
        }
        return inPercent ? area/absArea : area;
    }

    public int getProductAmount(Product product){
        int amount = 0;
        for(Section sec: sections){
            if(Product.isSame(sec.getProduct(), product)){
                amount += sec.getAmount();
            }
        }
        return amount;
    }



}
