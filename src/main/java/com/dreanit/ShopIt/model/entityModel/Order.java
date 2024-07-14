package com.dreanit.ShopIt.model.entityModel;

import com.dreanit.ShopIt.enums.OrderStatus;
import jakarta.persistence.*;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "`order`")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    public UUID id;
    public UUID customerId;

    @Setter
    @CreationTimestamp
    public LocalDateTime orderDate;
    @Enumerated(EnumType.STRING)
    public OrderStatus status = OrderStatus.PROCESSING;
    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    public List<OrderItem> orderItems=new ArrayList<>();


    public void setOrderItems(List<OrderItem> orderItems) {
        this.orderItems = orderItems;
        for (OrderItem item : orderItems) {
            item.setOrder(this);
        }
    }


}
