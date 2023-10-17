package com.example.learningspringbootproducts.product;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepo extends MongoRepository<Product, String> {
    List<Product> findProductsByPrice(double price);
    List<Product> findProductsByPriceLessThanEqual(double price);

    Product updateProductPriceById(String id, double newPrice);

    Object updateProductById(String number, int i);
}
