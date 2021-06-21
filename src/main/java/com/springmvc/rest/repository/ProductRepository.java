package com.springmvc.rest.repository;

import com.springmvc.rest.model.Product;

import java.util.List;

public interface ProductRepository {

    Product addProduct(Product p);
    Product getProductById(int id);
    Product updateProduct(Product p);
    Product deleteProductById(int id);
    List<Product> getAllProducts();
}
