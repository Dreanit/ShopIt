package com.dreanit.ShopIt.service;

import com.dreanit.ShopIt.dao.CategoryDao;
import com.dreanit.ShopIt.dao.ProductDao;
import com.dreanit.ShopIt.enums.ProductStatus;
import com.dreanit.ShopIt.model.entityModel.Category;
import com.dreanit.ShopIt.model.entityModel.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class ProductService {

    @Autowired
    private ProductDao productDao;

    @Autowired
    private CategoryDao categoryDao;

    public List<Product> getAllProducts() {
        return productDao.findAll();
    }
    public List<Category> getAllCategory() {
        return categoryDao.findAll();
    }
    public List<Product> getAllProductsFromStatus(ProductStatus status) {
        return productDao.findByStatus(status);
    }
    public List<Product> getProductFromCategory(UUID category_id) {
        return productDao.findByCategoryId(category_id);
    }

    public Product getProductById(UUID id) {
        return productDao.findById(id).orElse(null);
    }


    public Product saveProduct(Product product) {
        return productDao.save(product);
    }

    public void deleteProduct(UUID id) {
        productDao.deleteById(id);
    }
}
