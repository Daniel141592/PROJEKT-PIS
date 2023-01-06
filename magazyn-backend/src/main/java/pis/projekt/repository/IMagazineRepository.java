package pis.projekt.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pis.projekt.models.Magazine;

import java.util.List;

public interface IMagazineRepository extends JpaRepository<Magazine, Integer> {
    Magazine findMagazineById(Integer magazineId);

    List<Magazine> findMagazineByNameContaining(String name);
}
