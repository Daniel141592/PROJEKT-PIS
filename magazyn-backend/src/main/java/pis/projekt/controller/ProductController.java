package pis.projekt.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pis.projekt.interfaces.IProductService;
import pis.projekt.models.Product;

import java.util.List;

@RestController
@RequestMapping("/products")
@CrossOrigin(origins = "http://localhost:5173")
public class ProductController {
    private final IProductService productService;

    @Autowired
    public ProductController(IProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/all")
    public List<Product> getAllProducts() {
        return productService.findAllProducts();
    }

    @GetMapping("{id}")
    public Product getProductById(@PathVariable Integer id) {
        return productService.findProductById(id);
    }

    @GetMapping("/find")
    public List<Product> getProductsByName(@RequestParam("name") String name) {
        return productService.findProductsByNameContaining(name);
    }

    @PostMapping("/add")
    public Product addProduct(@RequestBody Product product){ return productService.addProduct(product);}

    @PostMapping("delete/{id}")
    public boolean deleteProduct(@PathVariable Integer id) {
        return productService.deleteProduct(id);
    }
}
