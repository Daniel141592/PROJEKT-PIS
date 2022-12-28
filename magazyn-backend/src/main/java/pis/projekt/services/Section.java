package pis.projekt.services;
import pis.projekt.models.Product;
import pis.projekt.utils.Pair;
import static java.lang.Math.abs;

public class Section {
    // let's try to get them in some sensible order
    // (upper left as first one, down left second, down right third...)
    private Pair[] cords;
    private Magazine magazine;
    private Product product;
    private String name;
    private Integer capacity;

    public Section(){
        Pair[] newCords = {new Pair(), new Pair(), new Pair(), new Pair()};
        cords = newCords;
        magazine = new Magazine();
        product = new Product();
        name = "";
        capacity = null;
    }

    public Section(Pair[] newCords, String newName, Product newProduct, Magazine newMagazine){
        cords = newCords;
        name = newName;
        product = newProduct;
        magazine = newMagazine;
    }

    public Pair[] getCords() {
        return cords;
    }

    public Magazine getMagazine() {
        return magazine;
    }

    public Product getProduct() {
        return product;
    }

    public void setCapacity(Integer capacity){
        this.capacity = capacity;
    }
    public int getCapacity() {
        if (capacity == null)
            capacity = calcMaxCapacity();
        return capacity;
    }

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
    // I think it should be opposite logic: capacity should be treated as
    // how many else can we fit inside, so imo    if (capacity > 0) {this.capacity -= 1;}
    // TODO: review logic of this function

    public int calcMaxCapacity() {
        // it isn't very accurate, there should be an algorithm to count it more properly
        // TODO: find better algorithm for this
        int length = abs(cords[1].second - cords[0].second);
        int width = abs(cords[3].first - cords[0].first);
        int propValue1 = (int)(length / product.getDimensions().first ) * (int)(width / product.getDimensions().second )
                * product.getStackSize();
        int propValue2 = (int)(width / product.getDimensions().first ) * (int)(length / product.getDimensions().second )
                * product.getStackSize();
        return propValue1 > propValue2 ? propValue1 : propValue2;
    }

    public boolean containsPoint(Pair point){
        boolean cond1 = point.first > cords[0].first;
        boolean cond2 = point.first < cords[2].first;
        boolean cond3 = point.second < cords[1].second;
        boolean cond4 = point.second > cords[3].second;
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

        for(int i = 0; i < getCords().length; i++){
            sindex = (i+1) % getCords().length;
            psum += getCords()[i].first * getCords()[sindex].second;
            nsum += getCords()[sindex].first * getCords()[i].second;

        }

        return abs(0.5*(psum - nsum));


    }

}
