package com.dreanit.ShopIt.dao;

import com.dreanit.ShopIt.model.UserAddress;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface UserAddressDao extends JpaRepository<UserAddress, UUID> {
}
