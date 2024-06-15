package com.dreanit.ShopIt.service;

import com.dreanit.ShopIt.dao.ProductDao;
import com.dreanit.ShopIt.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    @Autowired
    private ProductDao productDao;

    public List<Product> getAllProducts() {
        return productDao.findAll();
    }

    public Product getProductById(Long id) {
        return productDao.findById(id).orElse(null);
    }

    public Product saveProduct(Product product) {
        return productDao.save(product);
    }

    public void deleteProduct(Long id) {
        productDao.deleteById(id);
    }
}
