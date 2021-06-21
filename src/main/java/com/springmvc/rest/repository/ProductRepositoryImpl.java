package com.springmvc.rest.repository;

import com.springmvc.rest.model.Product;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;


import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public class ProductRepositoryImpl implements ProductRepository {

    @Autowired
    private SessionFactory factory;

    private Session getCurrentSession() {
        return factory.getCurrentSession();
    }

    @Override
    public Product addProduct(Product p) {
        Product response = null;

        if(getCurrentSession().save(p)!=null) {
            response = p;
        }
        return response;
    }

    @Override
    public Product getProductById(int id) {
        Product result = null;

        result = getCurrentSession().find(Product.class,id);

        return result;
    }

    @Override
    public Product updateProduct(Product product) {
        Product updateResult = null;
        updateResult = product;
        if(updateResult!=null) {
            getCurrentSession().update(product);
        }
        return product;
    }

    @Override
    public Product deleteProductById(int id) {
        Product p = getCurrentSession().find(Product.class,id);
        try{
            if(p!=null){
                getCurrentSession().remove(p);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return p;
    }

    @Override
    public List<Product> getAllProducts() {
        List<Product> productList;
        productList = getCurrentSession().createQuery("FROM Product", Product.class).list();
        return productList;
    }
}
