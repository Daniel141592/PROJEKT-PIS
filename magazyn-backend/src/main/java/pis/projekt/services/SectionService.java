package pis.projekt.services;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pis.projekt.interfaces.ISectionService;
import pis.projekt.models.Product;
import pis.projekt.models.Section;
import pis.projekt.repository.ISectionRepository;
import pis.projekt.utils.Pair;

import java.util.List;

@Service
public class SectionService implements ISectionService {
    @Autowired
    private ISectionRepository sectionRepository;

    @Override
    public List<Section> findAllSections() {
        return sectionRepository.findAll();
    }
    @Override
    public Section findSectionById(Integer sectionId) {
        return sectionRepository.findSectionById(sectionId);
    }

    @Override
    public List<Section> findSectionsByMagazine_Id(Integer magazineId) {
        return sectionRepository.findSectionsByMagazine_Id(magazineId);
    }

    @Override
    public List<Section> findSectionsByProduct_Id(Integer productId) {
        return sectionRepository.findSectionsByProduct_Id(productId);
    }

    @Override
    public Section addSection(Section section) {
        if(!checkCollision(section.getMagazineId(), section)) {
            return sectionRepository.save(section);
        }
        return null;
    }

    @Override
    public boolean deleteSection(Integer sectionId){
        if(sectionRepository.existsById(sectionId)){return false;}
        else{
            sectionRepository.deleteById(sectionId);
            return true;
        }
    }

    boolean checkCollision(Integer magazine_id, Section newSection) {
        List<Section> sections = sectionRepository.findSectionsByMagazine_Id(magazine_id);
        boolean isSame;
        for (Section section : sections) {
            isSame = true;
            for (Pair newSecPoint: newSection.getCoords()) {
                if (SectionService.containsPoint(section, newSecPoint))
                    return true;
            }
            for (Pair oldSecPoint: section.getCoords()){
                if (SectionService.containsPoint(newSection, oldSecPoint))
                    return true;
            }
            for (int i = 0; i < 4; i++) {
                if(section.getCoords() != newSection.getCoords()){
                    isSame = false;
                }
            }
            if (isSame)
                return true;
        }
        return false;
    }

    @Override
    public boolean addProduct(Integer sectionId, Integer amount) {
        Section section = sectionRepository.findSectionById(sectionId);
        Product product = section.getProduct();
        amount = section.getAmount() + amount;
        if (amount >= calcMaxCapacity(section) || amount >= product.getStackSize()) {
            return false;
        }
        section.setAmount(amount);
        sectionRepository.save(section);
        return true;
    }

    public static int calcMaxCapacity(Section section) {
        Product product = section.getProduct();
        int length = section.getLength();
        int width = section.getWidth();
        int propValue1 = (length / product.getDimensions().first) * (width / product.getDimensions().second)
                * product.getStackSize();
        int propValue2 = (width / product.getDimensions().first) * (length / product.getDimensions().second)
                * product.getStackSize();
        return Math.max(propValue1, propValue2);
    }

    public static boolean containsPoint(Section section, Pair point) {
        if (section.getBottomLeftPointY() + section.getLength() < point.first)
            return false;
        return (section.getBottomLeftPointX() + section.getWidth() >= point.second);
    }

    public static double calcArea(Section section) {
        return section.getWidth() * section.getLength();
    }
}
