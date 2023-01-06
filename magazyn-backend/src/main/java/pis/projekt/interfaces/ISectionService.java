package pis.projekt.interfaces;

import pis.projekt.models.Section;

import java.util.List;

public interface ISectionService {
    List<Section> findAllSections();
    Section findSectionById(Integer sectionId);

    List<Section> findSectionsByMagazine_Id(Integer magazineId);

    List<Section> findSectionsByProduct_Id(Integer productId);

    Section addSection(Section section);

    boolean deleteSection(Integer sectionId);

    boolean addProduct(Integer sectionId, Integer amount);
}
