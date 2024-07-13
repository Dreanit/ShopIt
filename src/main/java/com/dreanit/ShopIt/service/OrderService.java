package com.dreanit.ShopIt.service;

import com.dreanit.ShopIt.dao.OrderDao;
import com.dreanit.ShopIt.dao.OrderItemDao;
import com.dreanit.ShopIt.model.Order;
import com.dreanit.ShopIt.model.OrderItem;
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
}
