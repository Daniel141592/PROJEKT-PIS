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

    public double calcSpace(Magazine magazine) {
        return magazine.getWidth() * magazine.getLength();
    }

    public double calcEmptySpace(Magazine magazine, boolean inPercent) {
        double absArea = calcSpace(magazine);
        double area = absArea;
        List<Section> sections = sectionRepository.findSectionsByMagazine_Id(magazine.getId());
        for (Section section : sections){
            area -= SectionService.calcArea(section);
        }
        return inPercent ? area/absArea : area;
    }

    public int getProductAmount(Magazine magazine, Product product){
        int amount = 0;
        List<Section> sections = sectionRepository.findSectionsByMagazine_Id(magazine.getId());
        for (Section section : sections){
            if(Product.isSame(section.getProduct(), product)) {
                amount += section.getAmount();
            }
        }
        return amount;
    }
}
