package com.dreanit.ShopIt.service;

import com.dreanit.ShopIt.dao.OrderDao;
import com.dreanit.ShopIt.dao.OrderItemDao;
import com.dreanit.ShopIt.enums.OrderStatus;
import com.dreanit.ShopIt.model.entityModel.Order;
import com.dreanit.ShopIt.model.entityModel.OrderItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class OrderService {

    @Autowired
    OrderDao orderDao;
    @Autowired
    OrderItemDao orderItemDao;

    public Order saveOrder(Order order) {
        return orderDao.save(order);
    }

    public List<Order> getAllOrders() {
        return  orderDao.findAll();
    }

    public List<OrderItem> getAllItemOfOrder(UUID uuid) {
        return orderItemDao.findByOrderId(uuid);
    }

    public Order getOrderById(UUID orderId) {
       Order order= orderDao.findById(orderId).orElse(null);
        System.out.println(order);
        return order;
    }

    public Order updateOrderStatus(UUID orderId, OrderStatus status) {
        Order order= orderDao.findById(orderId).orElse(null);
        assert order != null;
        order.status=status;
        orderDao.save(order);
        return order;
    }

    public Order addItemToOrder(UUID orderId, OrderItem orderItem) {
        Order order = orderDao.findById(orderId).orElseThrow(() -> new RuntimeException("Order not found"));

        orderItem.setOrder(order);

        order.orderItems.add(orderItem);

        // Save the updated order
        orderDao.save(order);

        return order;
    }

    public Order removeItemFromOrder(UUID orderId, UUID itemId) {
        Order order = orderDao.findById(orderId).orElseThrow(() -> new RuntimeException("Order not found"));

        // Find the item to remove
        OrderItem itemToRemove = order.orderItems.stream()
                .filter(item -> item.getId().equals(itemId))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("OrderItem not found"));

        // Remove the item from the order
        order.orderItems.remove(itemToRemove);

        // Optionally, you may want to delete the item from the database
        orderItemDao.delete(itemToRemove);

        // Save the updated order
        orderDao.save(order);

        return order;
    }
}
