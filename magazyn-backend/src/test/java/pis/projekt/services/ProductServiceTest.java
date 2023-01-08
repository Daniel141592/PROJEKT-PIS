package pis.projekt.services;

import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import pis.projekt.models.Product;
import pis.projekt.repository.IProductRepository;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class ProductServiceTest {

    @Mock
    IProductRepository PR;

    @InjectMocks
    ProductService PService;

    @Test
    public void findAllProductsTest(){
        List<Product> products = new ArrayList<>();
        for (int i=0; i<10; i++){
            products.add(new Product());
        }

        when(PR.findAll()).thenReturn(products);
        Assertions.assertThat(PService.findAllProducts()).isSameAs(products);
    }

    @Test
    public void findProductByIdTest(){
        List<Product> products = new ArrayList<>();
        for (int i=0; i<10; i++){
            products.add(new Product(i));
        }

        when(PR.findProductById(5)).thenReturn(new Product(5));
        when(PR.findProductById(9)).thenReturn(new Product(9));

        Assertions.assertThat(PService.findProductById(5).getId()).isEqualTo(5);
        Assertions.assertThat(PService.findProductById(9).getId()).isEqualTo(9);
        Assertions.assertThat(PService.findProductById(2)).isNull();
    }

    @Test
    public void findProductsByNameContainingTest(){
        List<Product> products = new ArrayList<>();
        for (int i=0; i<10; i++){
            products.add(new Product(i));
        }

        when(PR.findProductsByNameContaining("dupa")).thenReturn(products);
        Assertions.assertThat(PService.findProductsByNameContaining("dupa")).isSameAs(products);
        Assertions.assertThat(PService.findProductsByNameContaining("cont")).isEmpty();
    }

    @Test
    public void addProductTest(){
        Product prod = new Product(666);

        when(PR.save(prod)).thenReturn(prod);
        Assertions.assertThat(PService.addProduct(prod)).isSameAs(prod);
    }

    @Test
    public void deleteProductFailTest(){
        when(PR.existsById(66)).thenReturn(false);
        Assertions.assertThat(PService.deleteProduct(66)).isFalse();
    }

    @Test
    public void deleteProductSuccessTest(){
        when(PR.existsById(20)).thenReturn(true);
        Assertions.assertThat(PService.deleteProduct(20)).isTrue();
    }

}
