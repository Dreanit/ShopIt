package com.dreanit.ShopIt.model.entityModel;

import com.dreanit.ShopIt.enums.ProductStatus;
import jakarta.persistence.*;
import lombok.Setter;
import org.hibernate.annotations.UuidGenerator;

import java.util.UUID;

@Entity
public class Product {

    @Setter
    @Id
    @UuidGenerator
    public UUID id;

    @Enumerated(EnumType.STRING)
    public ProductStatus status=ProductStatus.INACTIVE;
    public String name;
    public String description;
    public double price;
    public int quantity;

}
