package com.example.learningspringbootproducts.product;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepo productRepo;
    private final IdService idService;

    public List<Product> getAllProducts() {
        return productRepo.findAll();
    }

    public Product addProduct(NewProduct product) {
        return productRepo.save(new Product(
                idService.randomId(),
                product.name()
        ));
    }

    public Product findProductById(String id) {
        return productRepo.findById(id).orElseThrow();
    }

    public void removeProductById(String id) {
        productRepo.deleteById(id);
    }

    public Product updateProductById(String id) {
        Product legacyProduct = findProductById(id);
        Product updatedProduct = new Product(idService.randomId(), legacyProduct.name());
        removeProductById(id);
        return productRepo.save(updatedProduct);
    }
}
