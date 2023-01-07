package pis.projekt.models;

import jakarta.persistence.*;
import pis.projekt.utils.Pair;

@Entity
@Table(name = "sekcje")
public class Section {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private int id;
    @ManyToOne
    @JoinColumn(name = "magazyn_id", nullable = false)
    private Magazine magazine;
    @ManyToOne
    @JoinColumn(name = "produkt_id", nullable = false)
    private Product product;
    @Column(name = "name", nullable = false)
    private String name;
    @Column(name = "amount", nullable = false)
    private int amount;
    @Column(name = "length", nullable = false)
    private int length;
    @Column(name = "width", nullable = false)
    private int width;
    @Column(name = "bottom_left_point_x", nullable = false)
    private int bottomLeftPointX;
    @Column(name = "bottom_left_point_y", nullable = false)
    private int bottomLeftPointY;

    public Section(){
        magazine = new Magazine();
        product = new Product();
        name = "";
        length = 0;
        width = 0;
        bottomLeftPointX = 0;
        bottomLeftPointY = 0;
    }

    public Section(
            Magazine magazine,
            Product product,
            String name,
            Integer amount,
            int width,
            int length,
            int bottomLeftPointX,
            int bottomLeftPointY) {
        this.magazine = magazine;
        this.product = product;
        this.name = name;
        this.amount = amount;
        this.length = length;
        this.width = width;
        this.bottomLeftPointX = bottomLeftPointX;
        this.bottomLeftPointY = bottomLeftPointY;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Integer getMagazineId() {
        return magazine.getId();
    }

    public Product getProduct() {
        return product;
    }

    public int getAmount() {
        return amount;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getBottomLeftPointX() {
        return bottomLeftPointX;
    }

    public void setBottomLeftPointX(int bottomLeftPointX) {
        this.bottomLeftPointX = bottomLeftPointX;
    }

    public int getBottomLeftPointY() {
        return bottomLeftPointY;
    }

    public void setBottomLeftPointY(int bottomLeftPointY) {
        this.bottomLeftPointY = bottomLeftPointY;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setMagazine(Magazine magazine) {
        this.magazine = magazine;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public Pair[] getCoords() {
        Pair[] coords = new Pair[4];
        coords[0] = new Pair(bottomLeftPointX, bottomLeftPointY);
        coords[1] = new Pair(bottomLeftPointX + width, bottomLeftPointY);
        coords[2] = new Pair(bottomLeftPointX, bottomLeftPointY + length);
        coords[3] = new Pair(bottomLeftPointX + width, bottomLeftPointY + length);
        return coords;
    }
}
