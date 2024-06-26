package com.dreanit.ShopIt.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import org.hibernate.annotations.UuidGenerator;

import java.util.UUID;

@Entity
public class Product {

    @Id
    @UuidGenerator
    public UUID id;
    public String name;
    public String description;
    public double price;
    public int quantity;

}
