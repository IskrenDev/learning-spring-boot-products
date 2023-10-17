package com.example.learningspringbootproducts.product;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ProductServiceTest {

    ProductRepo productRepo = mock(ProductRepo.class);

    IdService idService = new IdService();

    ProductService productService = new ProductService(productRepo, idService);

    @Test
    void getAllProducts() {
        //GIVEN
        List<Product> expected = List.of(
                new Product("1", "name", 23.00),
                new Product("2", "name", 19.70)
        );
        //WHEN
        when(productRepo.findAll()).thenReturn(expected);
        List<Product> actual = productService.getAllProducts();
        //THEN
        verify(productRepo).findAll();
        assertEquals(expected, actual);
    }

    @Test
    void findProductById() {
    }

    @Test
    void removeProductById() {
    }

    @Test
    void updateProductById() {
    }
}