package pis.projekt.services;
import pis.projekt.models.Product;
import pis.projekt.utils.Pair;
import static java.lang.Math.abs;

public class SectionService {
    // let's try to get them in some sensible order
    // (upper left as first one, down left second, down right third...)
    private Pair[] cords;
    private MagazineService magazineService;
    private Product product;
    private String name;
    private Integer amount;

    public SectionService(){
        Pair[] newCords = {new Pair(), new Pair(), new Pair(), new Pair()};
        cords = newCords;
        magazineService = new MagazineService();
        product = new Product();
        name = "";
        amount = 0;
    }

    public SectionService(Pair[] newCords, String newName, Product newProduct, MagazineService newMagazineService){
        cords = newCords;
        name = newName;
        product = newProduct;
        magazineService = newMagazineService;
        amount = 0;
    }

    public Pair[] getCords() {
        return cords;
    }

    public void setCords(int cordIx, int first, int second)
    {
        cords[cordIx] = new Pair(first, second);
    }

    public MagazineService getMagazine() {
        return magazineService;
    }

    public Product getProduct() {
        return product;
    }

    public void setAmount(Integer capacity){
        this.amount = capacity;
    }
    public int getAmount() {
        return amount;
    }

    public String getName() {
        return name;
    }

    public void forceSetMagazine(MagazineService magazineService) {
        this.magazineService = magazineService;
    }

    // to be finished when database comes
    //public void setMagazine(Magazine magazine) { if(!magazine.checkCollision(this, ))}

    public void setProduct(Product product) {
        this.product = product;
    }

    public void addProduct(){
        if(amount < this.calcMaxCapacity() && amount < product.getStackSize())
            { this.amount += 1;}
    }

    public int calcMaxCapacity() {
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
