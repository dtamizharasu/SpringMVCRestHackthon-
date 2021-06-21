package com.springmvc.rest.service;

import com.springmvc.rest.model.Product;
import com.springmvc.rest.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    ProductRepository productRepository;

    @Override
    public Product addProduct(Product p) {
        return productRepository.addProduct(p);
    }

    @Override
    public Product getProductById(int id) {
        return productRepository.getProductById(id);
    }

    @Override
    public Product updateProduct(Product p) {
        return productRepository.updateProduct(p);
    }

    @Override
    public Product deleteProductById(int id) {
        return productRepository.deleteProductById(id);
    }

    @Override
    public List<Product> getAllProducts() {
        return productRepository.getAllProducts();
    }
}
