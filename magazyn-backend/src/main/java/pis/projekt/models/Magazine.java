package pis.projekt.models;

import jakarta.persistence.*;

@Entity
@Table(name = "magazyny")
public class Magazine {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private int id;
    @Column(name = "name", nullable = false)
    private String name;
    @Column(name = "length", nullable = false)
    private int length;
    @Column(name = "width", nullable = false)
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

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getLength() {
        return length;
    }

    public int getWidth() {
        return width;
    }
}
