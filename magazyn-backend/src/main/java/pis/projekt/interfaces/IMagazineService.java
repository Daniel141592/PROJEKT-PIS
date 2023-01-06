package pis.projekt.interfaces;

import pis.projekt.models.Magazine;

import java.util.List;

public interface IMagazineService {
    List<Magazine> findAllMagazines();

    Magazine findMagazineById(Integer magazineId);

    List<Magazine> findMagazineByName(String name);

    Magazine addMagazine(Magazine magazine);

    boolean deleteMagazine(Integer magazineId);

}
