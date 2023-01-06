package pis.projekt.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pis.projekt.interfaces.IMagazineService;
import pis.projekt.models.Magazine;
import pis.projekt.models.Product;
import pis.projekt.models.Section;
import pis.projekt.repository.IMagazineRepository;
import pis.projekt.repository.ISectionRepository;

import java.util.List;
import java.util.Vector;

@Service
public class MagazineService implements IMagazineService {

    @Autowired
    private IMagazineRepository magazineRepository;
    @Autowired
    private ISectionRepository sectionRepository;

    @Override
    public List<Magazine> findAllMagazines() {
        return magazineRepository.findAll();
    }

    @Override
    public Magazine findMagazineById(Integer magazineId) {
        return magazineRepository.findMagazineById(magazineId);
    }

    @Override
    public List<Magazine> findMagazineByName(String name) {
        return magazineRepository.findMagazineByNameContaining(name);
    }

    @Override
    public Magazine addMagazine(Magazine magazine) {
        return magazineRepository.save(magazine);
    }

    @Override
    public boolean deleteMagazine(Integer magazineId){
        if(magazineRepository.existsById(magazineId)){return false;}
        else{
            magazineRepository.deleteById(magazineId);
            return true;
        }
    }

    public static double calcSpace(Magazine magazine) {
        return magazine.getWidth() * magazine.getLength();
    }

    public static double calcEmptySpace(Magazine magazine, boolean inPercent) {
        double absArea = calcSpace(magazine);
        double area = absArea;
        List<Section> sections = magazine.getSections();
        for (Section section : sections){
            area -= SectionService.calcArea(section);
        }
        return inPercent ? (area/absArea * 100) : area;
    }

    public static int getProductAmount(Magazine magazine, Product product){
        int amount = 0;
        List<Section> sections = magazine.getSections();
        for (Section section : sections){
            if(Product.isSame(section.getProduct(), product)) {
                amount += section.getAmount();
            }
        }
        return amount;
    }

    public static Vector<Product> getProductVector(Magazine magazine){
        Vector<Product> productVector = new Vector<Product>();
        List<Section> sections = magazine.getSections();
        for(Section ss: sections){
            if(!productVector.contains(ss.getProduct()) && !Product.isSame(ss.getProduct(), new Product())){productVector.add(ss.getProduct());}
        }
        return productVector;
    }


    public static int getProductSections(Magazine magazine, Product product){
        int productSections = 0;
        List<Section> sections = magazine.getSections();
        for(Section ss: sections){
            if(product == ss.getProduct()){productSections += 1;}
        }
        return productSections;
    }
}
