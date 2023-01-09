package pis.projekt.controller;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.ArrayType;
import org.junit.Test;
import org.assertj.core.api.Assertions;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import pis.projekt.models.Product;
import pis.projekt.services.ProductService;

import java.util.LinkedList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@WebMvcTest(ProductController.class)
@RunWith(SpringRunner.class)
public class ProductControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ProductService productService;

    final ObjectMapper objectMapper = new ObjectMapper();

    @Test
    public void getAllProductsTest() throws Exception{
        List<Product> products = new LinkedList<>();
        for (int i=0; i<7;++i){
            products.add(new Product(i, "prod", i+1, i+2, i+3));
        }

        when(productService.findAllProducts()).thenReturn(products);

        RequestBuilder req = MockMvcRequestBuilders.get("/products/all");
        String response = mockMvc.perform(req).andReturn().getResponse().getContentAsString();
        List<Product> retProducts = objectMapper.readValue(response, new TypeReference<List<Product>>() {});

        Assertions.assertThat(retProducts.size()).isEqualTo(7);
        Assertions.assertThat(retProducts.get(6).getStackSize()).isEqualTo(9);
    }

    @Test
    public void getProductByIdTest() throws Exception{
        int i = 4;
        Product product = new Product(i, "prod", i+1, i+2, i+3);
        when(productService.findProductById(i)).thenReturn(product);

        RequestBuilder req = MockMvcRequestBuilders.get("/products/4");
        String response = mockMvc.perform(req).andReturn().getResponse().getContentAsString();
        Product actProduct = objectMapper.readValue(response, new TypeReference<Product>() {});

        Assertions.assertThat(actProduct.getId()).isEqualTo(4);
        Assertions.assertThat(actProduct.getStackSize()).isEqualTo(7);
    }

    @Test
    public void getProductsByNameTest() throws Exception{
        List<Product> products = new LinkedList<>();
        for (int i=0; i<7;++i){
            products.add(new Product(i, "prod", i+1, i+2, i+3));
        }
        when(productService.findProductsByNameContaining("pro")).thenReturn(products);
        RequestBuilder req = MockMvcRequestBuilders.get("/products/find?name=pro");
        String response = mockMvc.perform(req).andReturn().getResponse().getContentAsString();
        List<Product> actProducts = objectMapper.readValue(response, new TypeReference<List<Product>>() {});

        Assertions.assertThat(actProducts.size()).isEqualTo(7);
        Assertions.assertThat(actProducts.get(5).getStackSize()).isEqualTo(8);

        req = MockMvcRequestBuilders.get("/products/find?name=dupa");
        response = mockMvc.perform(req).andReturn().getResponse().getContentAsString();
        actProducts = objectMapper.readValue(response, new TypeReference<List<Product>>() {});
        Assertions.assertThat(actProducts).isEmpty();
    }

    @Test
    public void addProductTest() throws Exception{
        int i = 4;
        Product product = new Product(i, "prod", i+1, i+2, i+3);
        when(productService.addProduct(any())).thenReturn(product);
        RequestBuilder req = MockMvcRequestBuilders
                .post("/products/add")
                .content(objectMapper.writeValueAsString(product))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON);
        String response = mockMvc.perform(req).andReturn().getResponse().getContentAsString();
        Product actProduct = objectMapper.readValue(response, new TypeReference<Product>() {});

        Assertions.assertThat(actProduct.getName()).isEqualTo("prod");
        Assertions.assertThat(actProduct.getStackSize()).isEqualTo(7);
    }

    @Test
    public void deleteProductTest() throws Exception{
        int i = 4;
        Product product = new Product(i, "prod", i+1, i+2, i+3);
        when(productService.deleteProduct(4)).thenReturn(true);
        when(productService.deleteProduct(2137)).thenReturn(false);

        RequestBuilder req = MockMvcRequestBuilders
                .post("/products/delete/4")
                .content(objectMapper.writeValueAsString(4));
        String response = mockMvc.perform(req).andReturn().getResponse().getContentAsString();
        boolean result = objectMapper.readValue(response, new TypeReference<Boolean>() {});
        Assertions.assertThat(result).isTrue();

        req = MockMvcRequestBuilders
                .post("/products/delete/2137")
                .content(objectMapper.writeValueAsString(2137));
        response = mockMvc.perform(req).andReturn().getResponse().getContentAsString();
        result = objectMapper.readValue(response, new TypeReference<Boolean>() {});
        Assertions.assertThat(result).isFalse();
    }
}
