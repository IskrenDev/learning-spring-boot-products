package com.example.learningspringbootproducts.product;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepo productRepo;

    public List<Product> getAllProducts() {
        return productRepo.findAll();
    }

    public Product addProduct(Product product) {
        return productRepo.save(product);
    }

    public Product findProductById(String id) {
        return productRepo.findById(id).orElseThrow();
    }

    public void removeProductById(String id) {
        productRepo.deleteById(id);
    }

    public Product updateProductById(String id) {
        Product legacyProduct = productRepo.findById(id).orElseThrow();
        Product updatedProduct = new Product(null, legacyProduct.name());
        productRepo.delete(legacyProduct);
        return productRepo.save(updatedProduct);
    }
}
