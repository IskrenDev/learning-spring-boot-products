package com.example.learningspringbootproducts.product;

import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

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
        //GIVEN
        Product expected = new Product("1", "name", 23.00);

        //WHEN

        when(productRepo.findById("1")).thenReturn(Optional.of(expected));
        Product actual = productService.findProductById("1");
        //THEN
        verify(productRepo).findAll();
        assertEquals(expected, actual);
    }

    @Test
    void findProductById_invalidId_thenReturnException() {
        //WHEN
        when(productRepo.findById("1")).thenThrow(new NoSuchElementException());
        //THEN
        assertThrows(NoSuchElementException.class, () -> productRepo.findById("1"));
    }

    @Test
    void removeProductById() {
        doNothing().when(productRepo).deleteById(anyString());
        productService.removeProductById("1");

        verify(productRepo, times(1)).deleteById("1");
    }

    @Test
    void testUpdateProductById() {
        Product legacyProduct = new Product("1", "itemName", 100.0);
        Product updatedProduct = new Product("1", "itemName", 200.0);

        when(productRepo.findById(legacyProduct.id())).thenReturn(Optional.of(legacyProduct));
        when(productRepo.save(any(Product.class))).thenReturn(updatedProduct);

        Product result = productService.updateProductById("1", 200.0);

        verify(productRepo, times(1)).findById(legacyProduct.id());
        verify(productRepo, times(1)).save(any(Product.class));

        assertEquals(updatedProduct, result);
    }
}