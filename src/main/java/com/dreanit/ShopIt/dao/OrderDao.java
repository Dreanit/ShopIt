package com.dreanit.ShopIt.dao;

import com.dreanit.ShopIt.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface OrderDao extends JpaRepository<Order, UUID> {
    
}
