package pis.projekt.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pis.projekt.Config;
import pis.projekt.interfaces.ISectionService;
import pis.projekt.models.Section;
import pis.projekt.models.requests.AddProductRequest;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/sections")
@CrossOrigin(origins = {Config.CORS_URL})
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
    public List<Section> getSectionsByUnitId(
            @RequestParam(value = "productid", required = false) Integer productId,
            @RequestParam(value = "magazineid", required = false) Integer magazineId) {
        if (productId != null)
            return sectionService.findSectionsByProduct_Id(productId);
        else if (magazineId != null)
            return sectionService.findSectionsByMagazine_Id(magazineId);
        else
            return sectionService.findSectionsByMagazine_Id(null);
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
