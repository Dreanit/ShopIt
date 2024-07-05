package com.dreanit.ShopIt.dto;

import com.dreanit.ShopIt.enums.ProductStatus;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Data
public class ProductUpdateDTO {
    public UUID id;
    @Setter
    @Enumerated(EnumType.STRING)
    public ProductStatus status;
    public String name;
    public String description;
    public Double price;
    public Integer quantity;


}
