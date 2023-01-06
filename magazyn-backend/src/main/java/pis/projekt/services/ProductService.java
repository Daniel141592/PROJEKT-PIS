package pis.projekt.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pis.projekt.interfaces.IProductService;
import pis.projekt.models.Product;
import pis.projekt.repository.IProductRepository;

import java.util.List;

@Service
public class ProductService implements IProductService {
    @Autowired
    private IProductRepository productRepository;

    @Override
    public List<Product> findAllProducts() {
        return productRepository.findAll();
    }
    @Override
    public Product findProductById(Integer productId) {
        return productRepository.findProductById(productId);
    }

    @Override
    public List<Product> findProductsByNameContaining(String name) {
        return productRepository.findProductsByNameContaining(name);
    }

    @Override
    public Product addProduct(Product product){
        return productRepository.save(product);
    }

    @Override
    public boolean deleteProduct(Integer productId){
        if(productRepository.existsById(productId)){return false;}
        else{
            productRepository.deleteById(productId);
            return true;
        }
    }
}
