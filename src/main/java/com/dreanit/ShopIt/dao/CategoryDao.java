package com.dreanit.ShopIt.dao;


import com.dreanit.ShopIt.model.entityModel.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface CategoryDao extends JpaRepository<Category, UUID> {

}
