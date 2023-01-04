package pis.projekt.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pis.projekt.interfaces.IMagazineService;
import pis.projekt.models.Magazine;
import pis.projekt.models.Section;

import java.util.List;

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
}
