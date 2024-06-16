package com.dreanit.ShopIt.dao;

import com.dreanit.ShopIt.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface UserDao extends JpaRepository<User, UUID> {
}
