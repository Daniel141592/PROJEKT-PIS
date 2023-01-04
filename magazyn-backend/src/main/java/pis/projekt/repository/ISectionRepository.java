package pis.projekt.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pis.projekt.models.Section;

import java.util.List;

public interface ISectionRepository extends JpaRepository<Section, Integer> {
    Section findSectionById(Integer sectionId);

    List<Section> findSectionsByMagazine_Id(Integer magazineId);

    List<Section> findSectionsByProduct_Id(Integer productId);

}
