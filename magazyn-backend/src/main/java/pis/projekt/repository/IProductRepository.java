package pis.projekt.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pis.projekt.models.Product;

import java.util.List;

public interface IProductRepository extends JpaRepository<Product, Integer> {
    Product findProductById(Integer productId);

    List<Product> findProductsByNameContaining(String name);

}
