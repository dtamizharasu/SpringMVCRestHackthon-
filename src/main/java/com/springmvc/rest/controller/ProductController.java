package com.springmvc.rest.controller;


import com.springmvc.rest.model.Product;
import com.springmvc.rest.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/*
* Product Management System
* Api methods
* 1. Add Product
* 2. Get All Products
* 3. Get Product By id
* 4. Update Product By Id
* 5. Delete Product Id*/

@RestController
@RequestMapping("/product/api")//http://localhost:8080/SpringMVCRestHackthon_war_exploded/product/api/
public class ProductController {

    @Autowired
    ProductService service;

    // Get All the Products Details
    @GetMapping("/products")
    public ResponseEntity<?> getAllNotes() {

        List<Product> productList=service.getAllProducts();
        return new ResponseEntity<>(productList, HttpStatus.OK);
    }

    // Add the product
    @PostMapping("/addprodcut")
    public ResponseEntity<?> addProduct(@RequestBody Product product){
        ResponseEntity<?>	re;

        try {

            Product responseProd=service.addProduct(product);
            if (responseProd !=null) {
                re= new ResponseEntity<Product>(responseProd,HttpStatus.CREATED);
            }else {
                re= new ResponseEntity<String>("Conflict Data",HttpStatus.CONFLICT);
            }

        }catch (Exception e) {
            e.printStackTrace();
            re= new ResponseEntity<String>("Internal Error",HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return re;
    }

    // Get All the Products Details
    @GetMapping("/{productId}")
    public ResponseEntity<?> getProductById(@PathVariable int productId) {
        Product product;
        ResponseEntity<?>	re;
        try {

            product=service.getProductById(productId);
            if (product !=null) {
                re= new ResponseEntity<Product>(product,HttpStatus.CREATED);
            }else {
                re= new ResponseEntity<String>("Conflict Data",HttpStatus.CONFLICT);
            }

        }catch (Exception e) {
            e.printStackTrace();
            re= new ResponseEntity<String>("Internal Error",HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return re;
    }

    // Update the Product details
    @PutMapping("{productId}")
    public ResponseEntity<?> updateProductByID(@PathVariable Integer productId,@RequestBody Product product){
        Product productStatus;
        ResponseEntity<?>	re;
        try {
            product.setId(productId);
            productStatus = service.updateProduct(product);
            if (productStatus !=null) {
                re= new ResponseEntity<Product>(productStatus,HttpStatus.ACCEPTED);
            }else {
                re= new ResponseEntity<String>("Conflict Data",HttpStatus.CONFLICT);
            }

        }catch (Exception e) {
            e.printStackTrace();
            re= new ResponseEntity<String>("Internal Error",HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return re;
    }

    // Delete the Product
    @DeleteMapping("{productId}")
    public ResponseEntity<?> deletedProductById(@PathVariable int productId){
        ResponseEntity<?>	re;
        Product productStatus;
        try {
            productStatus = service.deleteProductById(productId);
            if (productStatus !=null) {
                re= new ResponseEntity<Product>(productStatus,HttpStatus.OK);
            }else {
                re= new ResponseEntity<String>("Conflict Data",HttpStatus.CONFLICT);
            }

        }catch (Exception e) {
            e.printStackTrace();
            re= new ResponseEntity<String>("Internal Error",HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return re;
    }

}
