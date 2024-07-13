package com.dreanit.ShopIt.wrapper;

import com.dreanit.ShopIt.enums.OrderStatus;
import com.dreanit.ShopIt.enums.ProductStatus;
import com.dreanit.ShopIt.model.OrderItem;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public class OrderDetails {
    public UUID id;
    public UUID customerId;
    public LocalDateTime orderDate;
    public OrderStatus status;
    public List<OrderDetailItem> orderItems; // Updated to a custom class

    public OrderDetails(
            UUID id,
            UUID customerId,
            LocalDateTime orderDate,
            OrderStatus status,
            List<OrderDetailItem> orderItems) {
        this.id = id;
        this.customerId = customerId;
        this.orderDate = orderDate;
        this.status = status;
        this.orderItems = orderItems;
    }

    public static class OrderDetailItem {
        public UUID productId;
        public String productName; // Add fields as needed
        public ProductStatus productStatus; // Add fields as needed
        public int quantity;
        public double price;

        public OrderDetailItem(UUID productId, String productName,ProductStatus productStatus, int quantity, double price) {
            this.productId = productId;
            this.productName = productName;
            this.productStatus = productStatus;
            this.quantity = quantity;
            this.price = price;
        }
    }
}
