package com.dreanit.ShopIt.controller;

import com.dreanit.ShopIt.model.APIResponse;
import com.dreanit.ShopIt.model.Order;
import com.dreanit.ShopIt.model.Product;
import com.dreanit.ShopIt.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(("/order"))
public class OrderController {

    @Autowired
    private OrderService orderService;

    @PostMapping("/createOrder")
    public ResponseEntity<APIResponse<Order>> createProduct(@RequestBody Order order){
        try{

            Order data = orderService.saveOrder(order);
            APIResponse<Order> response = new APIResponse<>(true, data, "Order Created Successfully");
            return new ResponseEntity<>(response, HttpStatus.OK);
        }catch (Exception e){
            APIResponse<Order> response = new APIResponse<>(false, null, e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }
    }

}
