package com.dreanit.ShopIt.dao;

import com.dreanit.ShopIt.enums.ProductStatus;
import com.dreanit.ShopIt.model.entityModel.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface ProductDao extends JpaRepository<Product, UUID> {
    List<Product> findByStatus(ProductStatus status);

    List<Product> findByCategoryId(UUID categoryId);
}
