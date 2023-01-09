package pis.projekt.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pis.projekt.interfaces.ISectionService;
import pis.projekt.models.Section;
import pis.projekt.models.requests.AddProductRequest;

import java.util.List;

@RestController
@RequestMapping("/sections")
@CrossOrigin(origins = {"http://localhost:5173", "http://localhost"})
public class SectionController {
    private final ISectionService sectionService;

    @Autowired
    public SectionController(ISectionService sectionService) {
        this.sectionService = sectionService;
    }

    @GetMapping("/all")
    public List<Section> getAllSections() {
        return sectionService.findAllSections();
    }

    @GetMapping("{id}")
    public Section getSectionById(@PathVariable Integer id) {
        return sectionService.findSectionById(id);
    }

    @GetMapping("/find")
    public List<Section> getSectionsByMagazineId(@RequestParam("magazineid") Integer magazineId) {
        return sectionService.findSectionsByMagazine_Id(magazineId);
    }

    //TODO: make optional arguments!
    @GetMapping("/find2")
    public List<Section> getSectionsByProductId(@RequestParam("productid") Integer productId) {
        return sectionService.findSectionsByProduct_Id(productId);
    }

    @PostMapping("/add")
    public Section addSection(@RequestBody Section section) {
        return sectionService.addSection(section);
    }

    @PostMapping("/delete/{id}")
    public boolean deleteSection(@PathVariable Integer id) {
        return sectionService.deleteSection(id);
    }

    @PostMapping("/product/add")
    public boolean addProduct(@RequestBody AddProductRequest request) {
        return sectionService.addProduct(request.getSectionId(), request.getAmount());
    }
}
