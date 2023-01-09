package pis.projekt.controller;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pis.projekt.interfaces.IMagazineService;
import pis.projekt.models.Magazine;
import pis.projekt.utils.Report;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Vector;

@RestController
@RequestMapping("/magazines")
@CrossOrigin(origins = "http://localhost:5173")
public class MagazineController {
    private final IMagazineService magazineService;

    @Autowired
    public MagazineController(IMagazineService magazineService) {
        this.magazineService = magazineService;
    }

    @GetMapping("/all")
    public List<Magazine> getAllMagazines() {
        return magazineService.findAllMagazines();
    }

    @GetMapping("{id}")
    public Magazine getMagazineById(@PathVariable Integer id) {
        return magazineService.findMagazineById(id);
    }

    @GetMapping("/find")
    public List<Magazine> getMagazineByName(@RequestParam("name") String name) {
        return magazineService.findMagazineByName(name);
    }

    @PostMapping("/add")
    public Magazine addMagazine(@RequestBody Magazine magazine) {
        return magazineService.addMagazine(magazine);
    }

    @PostMapping("/delete/{id}")
    public boolean deleteMagazine(@PathVariable Integer id) {
        return magazineService.deleteMagazine(id);
    }

    @GetMapping("/report/{id}")
    public ResponseEntity createAndDownloadReport(@PathVariable Integer id) throws IOException {
        String fileName = magazineService.createAndStashReport(id);
        Path path = Paths.get(fileName);
        Resource resource = null;
        try {
            resource = new UrlResource(path.toUri());
        }
        catch (MalformedURLException e) {
            e.printStackTrace();;
        }
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFilename() + "\"")
                .body(resource);

    }

    @GetMapping("/report/search")
    public Vector<String> searchInReports(@RequestParam(name = "search") String search) {
        return magazineService.searchInReports(search);
    }
}
