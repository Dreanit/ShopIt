package com.dreanit.ShopIt.dao;

import com.dreanit.ShopIt.model.entityModel.Order;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface OrderDao extends JpaRepository<Order, UUID> {
    @Override
    Optional<Order> findById(UUID id);
}
