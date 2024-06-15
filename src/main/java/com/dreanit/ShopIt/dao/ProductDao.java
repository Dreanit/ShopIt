package com.dreanit.ShopIt.dao;

import com.dreanit.ShopIt.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductDao extends JpaRepository<Product,Long> {

}
