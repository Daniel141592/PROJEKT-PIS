package pis.projekt.interfaces;

import org.json.JSONObject;
import pis.projekt.models.Magazine;

import java.io.IOException;
import java.util.List;
import java.util.Vector;

public interface IMagazineService {
    List<Magazine> findAllMagazines();

    Magazine findMagazineById(Integer magazineId);

    List<Magazine> findMagazineByName(String name);

    Magazine addMagazine(Magazine magazine);

    boolean deleteMagazine(Integer magazineId);

    Vector<String> searchInReports(String search);
    
    String createAndStashReport(Integer magazineId) throws IOException;

}
