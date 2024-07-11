package com.dreanit.ShopIt.service;

import com.dreanit.ShopIt.dao.OrderDao;
import com.dreanit.ShopIt.model.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderService {

    @Autowired
    OrderDao orderDao;

    public Order saveOrder(Order order) {
        return orderDao.save(order);
    }
}
