package com.dreanit.ShopIt.service;

import com.dreanit.ShopIt.dao.UserDao;
import com.dreanit.ShopIt.model.User;
import com.dreanit.ShopIt.model.UserAddress;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    @Autowired
    private UserDao userDao;


    @Transactional
    public User createUser(String firstName,String lastName, String email, String password, List<UserAddress> addresses) {
            User user = new User(null, firstName, lastName, email, password, null, addresses);
            for (UserAddress address : addresses) {
                address.setUser(user); // Set the user reference in each address
            }
            userDao.save(user);
            return userDao.getReferenceById(user.getId());

    }

    public List<User> getAllUsers() {
        return userDao.findAll();
    }
}
