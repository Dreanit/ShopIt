package com.dreanit.ShopIt.model.entityModel;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Setter;
import org.hibernate.annotations.UuidGenerator;

import java.util.Set;
import java.util.UUID;

@Entity
public class Category {

    @Setter
    @Id
    @UuidGenerator
    public UUID id;

    public String name;
    public String description;
    public  String imageUrl;

    @JsonIgnore
    // One-to-Many relationship with Product
    @OneToMany(mappedBy = "category", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    public Set<Product> products;
}
