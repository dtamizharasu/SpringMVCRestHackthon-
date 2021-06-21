package com.springmvc.rest.service;

import com.springmvc.rest.model.Product;

import java.util.List;

public interface ProductService {

    Product addProduct(Product p);
    public Product getProductById(int id);
    public Product updateProduct(Product p);
    public Product deleteProductById(int id);
    public List<Product> getAllProducts();
}
