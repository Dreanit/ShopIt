package com.dreanit.ShopIt.controller;


import com.dreanit.ShopIt.dto.UserRegistrationDTO;
import com.dreanit.ShopIt.model.User;
import com.dreanit.ShopIt.model.UserAddress;
import com.dreanit.ShopIt.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping("/all")
    public ResponseEntity<List<User>> getAllUsers() {
        List<User> users = userService.getAllUsers();
        if (!users.isEmpty()) {
            return ResponseEntity.ok(users);
        } else {
            return ResponseEntity.noContent().build();
        }
    }

    @PostMapping("/register")
    public ResponseEntity<User> registerUser(@RequestBody UserRegistrationDTO registrationDto) {

        List<UserAddress> addresses = registrationDto.getAddresses().stream()
                .map(dto -> new UserAddress(
                        null,
                        dto.getStreet(),
                        dto.getCity(),
                        dto.getState(),
                        dto.getZipCode(),
                        dto.getCountry(),
                        null // User reference will be set in the service
                ))
                .collect(Collectors.toList());

       User user= userService.createUser(
                registrationDto.getFirstName(),
                registrationDto.getLastName(),
                registrationDto.getEmail(),
                registrationDto.getPassword(),
                addresses
        );
        return new  ResponseEntity<>(user,HttpStatus.CREATED );
    }
}
