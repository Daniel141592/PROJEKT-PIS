package pis.projekt.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pis.projekt.interfaces.IProductService;
import pis.projekt.interfaces.IReportModelService;
import pis.projekt.models.Product;
import pis.projekt.models.ReportModel;

import java.util.List;

@RestController
@RequestMapping("/reports")
@CrossOrigin(origins = "http://localhost:5173")
public class ReportModelController {
    private final IReportModelService reportModelService;

    @Autowired
    public ReportModelController(IReportModelService reportModelService) {
        this.reportModelService = reportModelService;
    }

    @GetMapping("/all")
    public List<ReportModel> getAllReportModels() {
        return reportModelService.findAllReportModels();
    }

    @GetMapping("{id}")
    public ReportModel getProductById(@PathVariable Integer id) {
        return reportModelService.findReportModelById(id);
    }

}
