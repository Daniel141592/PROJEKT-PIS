package pis.projekt.models;

import pis.projekt.services.MagazineService;
import pis.projekt.utils.Pair;

public class Section {
    private int id;
    private Magazine magazine;
    private Product product;
    private String name;
    private Integer amount;
    private int point1Length;
    private int point1Width;
    private int point2Length;
    private int point2Width;
    private int point3Length;
    private int point3Width;
    private int point4Length;
    private int point4Width;

    public Section(){
        magazine = new Magazine();
        product = new Product();
        name = "";
        point1Length = 0;
        point1Width = 0;
        point2Length = 0;
        point2Width = 0;
        point3Length = 0;
        point3Width = 0;
        point4Length = 0;
        point4Width = 0;
    }

    public Section(int id, Magazine magazine, Product product, String name, Integer amount, int point1Length, int point1Width, int point2Length, int point2Width,  int point3Length, int point3Width, int point4Length, int point4Width){
        this.id = id;
        this.magazine = magazine;
        this.product = product;
        this.name = name;
        this.amount = amount;
        this.point1Length = point1Length;
        this.point1Width = point1Width;
        this.point2Length = point2Length;
        this.point2Width = point2Width;
        this.point3Length = point3Length;
        this.point3Width = point3Width;
        this.point4Length = point4Length;
        this.point4Width = point4Width;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Magazine getMagazine() {
        return magazine;
    }

    public Product getProduct() {
        return product;
    }

    public Integer getAmount() {
        return amount;
    }

    public int getPoint1Length() {
        return point1Length;
    }

    public int getPoint1Width() {
        return point1Width;
    }

    public int getPoint2Length() {
        return point2Length;
    }

    public int getPoint2Width() {
        return point2Width;
    }

    public int getPoint3Length() {
        return point3Length;
    }

    public int getPoint3Width() {
        return point3Width;
    }

    public int getPoint4Length() {
        return point4Length;
    }

    public int getPoint4Width() {
        return point4Width;
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

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public void setPoint1Length(int point1Length) {
        this.point1Length = point1Length;
    }

    public void setPoint1Width(int point1Width) {
        this.point1Width = point1Width;
    }

    public void setPoint2Length(int point2Length) {
        this.point2Length = point2Length;
    }

    public void setPoint2Width(int point2Width) {
        this.point2Width = point2Width;
    }

    public void setPoint3Length(int point3Length) {
        this.point3Length = point3Length;
    }

    public void setPoint3Width(int point3Width) {
        this.point3Width = point3Width;
    }

    public void setPoint4Length(int point4Length) {
        this.point4Length = point4Length;
    }

    public void setPoint4Width(int point4Width) {
        this.point4Width = point4Width;
    }
}
