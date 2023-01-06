package pis.projekt.interfaces;

import pis.projekt.models.Product;

import java.util.List;

public interface IProductService {
    List<Product> findAllProducts();
    Product findProductById(Integer productId);

    List<Product> findProductsByNameContaining(String name);
}
