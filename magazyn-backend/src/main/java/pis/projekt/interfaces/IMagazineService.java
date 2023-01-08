package pis.projekt.interfaces;

import pis.projekt.models.Magazine;

import java.io.IOException;
import java.util.List;

public interface IMagazineService {
    List<Magazine> findAllMagazines();

    Magazine findMagazineById(Integer magazineId);

    List<Magazine> findMagazineByName(String name);

    Magazine addMagazine(Magazine magazine);

    boolean deleteMagazine(Integer magazineId);

    String createAndStashReport(Integer magazineId) throws IOException;

}
