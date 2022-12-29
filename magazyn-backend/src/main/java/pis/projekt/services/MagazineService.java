package pis.projekt.services;

import pis.projekt.models.Product;
import pis.projekt.utils.Pair;

import java.util.Vector;

import static java.lang.Math.abs;
import static java.lang.Math.round;

public class MagazineService {
    private int id;
    private String name;
    private Pair dimensions;
    private Vector<SectionService> sectionServices;

    public MagazineService(){
        id = 0;
        name = "";
        dimensions = new Pair();
        sectionServices = new Vector<SectionService>();
    }

    public MagazineService(int newId, String newName, int newLength, int newWidth){
        id = newId;
        name = newName;
        dimensions = new Pair(newLength, newWidth);
        sectionServices = new Vector<SectionService>();
    }

    public MagazineService(int id, String name, int length, int width, SectionService sectionService){
        this(id, name, length, width);
        this.sectionServices.add(sectionService);
    }

    public MagazineService(int id, String name, int length, int width, Vector<SectionService> sectionServices){
        this(id, name, length, width);
        this.sectionServices = sectionServices;
    }

    public String getName() {
        return name;
    }

    public Vector<SectionService> getSections(){
        return sectionServices;
    }

    public int getId() {
        return id;
    }

    public Pair getDimensions() { return dimensions;}

    public int getSectionsAmount(){ return sectionServices.size(); }

    boolean checkCollision(SectionService newSectionService){
        boolean isSame;
        for(SectionService sec: sectionServices){
            isSame = true;
            for(Pair newSecPoint: newSectionService.getCords()){
                if(sec.containsPoint(newSecPoint)){ return true; }
            }
            for(Pair oldSecPoint: sec.getCords()){
                if(newSectionService.containsPoint(oldSecPoint)){ return true; }
            }
            for(int i=0; i<4; i++){
                if(sec.getCords() != newSectionService.getCords()){
                    isSame = false;
                }
            }
            if(isSame){ return true; }
        }
        return false;
    }

    // return true if added, else return false
    public boolean addSection(SectionService newSectionService){
        if(!checkCollision(newSectionService)){
            sectionServices.add(newSectionService);
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
        for(SectionService sec: sectionServices){
            area -= sec.calcArea();
        }
        return inPercent ? area/absArea : area;
    }

    public int getProductAmount(Product product){
        int amount = 0;
        for(SectionService sec: sectionServices){
            if(Product.isSame(sec.getProduct(), product)){
                amount += sec.getAmount();
            }
        }
        return amount;
    }

    public Vector<Product> getProductVector(){
        Vector<Product> productVector = new Vector<Product>();

        for(SectionService ss: sectionServices){
            if(!productVector.contains(ss.getProduct())){productVector.add(ss.getProduct());}
        }
        return productVector;
    }

    public int getProductAmountInMagazine(Product product){
        int productAmount = 0;
        for(SectionService ss: sectionServices){
            if(product == ss.getProduct()){productAmount += ss.getAmount();}
        }
        return productAmount;
    }

    public int getProductSections(Product product){
        int productSections = 0;
        for(SectionService ss: sectionServices){
            if(product == ss.getProduct()){productSections += 1;}
        }
        return productSections;
    }

}
