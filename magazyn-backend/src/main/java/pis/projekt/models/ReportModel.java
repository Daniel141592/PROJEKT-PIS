package pis.projekt.models;

import jakarta.persistence.*;


@Entity
@Table(name = "raporty")
public class ReportModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;
    @Lob
    @Column(name = "file", nullable = false)
    private byte[] pdfFile;


    public ReportModel(Integer newId, byte[] fileBytes){
        this.id = newId;
        this.pdfFile = fileBytes;
    }

    public ReportModel(){

    }

    public Integer getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public byte[] getPdfFile() {
        return pdfFile;
    }

    public void setPdfFile(byte[] pdfFile) {
        this.pdfFile = pdfFile;
    }

}
