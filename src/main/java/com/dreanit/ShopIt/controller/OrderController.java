package com.dreanit.ShopIt.controller;

import com.dreanit.ShopIt.model.APIResponse;
import com.dreanit.ShopIt.model.Order;
import com.dreanit.ShopIt.model.OrderItem;
import com.dreanit.ShopIt.model.Product;
import com.dreanit.ShopIt.service.OrderService;
import com.dreanit.ShopIt.service.ProductService;
import com.dreanit.ShopIt.wrapper.OrderDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@RequestMapping(("/order"))
public class OrderController {

    @Autowired
    private OrderService orderService;

    @Autowired
    private ProductService productService;

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
    @GetMapping("/all")
    public ResponseEntity<APIResponse<List<OrderDetails>>> getAllOrders(){
        try{
            List<Order> data = orderService.getAllOrders();
          List<OrderDetails> dataList= data.stream().map(order -> {
                List<OrderDetails.OrderDetailItem> items=orderService.getAllItemOfOrder(order.id).stream()
                        .map(orderItem -> {
                            Product product = productService.getProductById(orderItem.getProductId());
                            return new OrderDetails.OrderDetailItem(
                                    orderItem.getProductId(),
                                    product.name,
                                    product.status,
                                    orderItem.getQuantity(),
                                    orderItem.getPrice()
                            );
                        })
                        .collect(Collectors.toList());

                return new OrderDetails(
                        order.id,
                        order.customerId,
                        order.orderDate,
                        order.status,
                        items
                );
            }).toList();

            APIResponse<List<OrderDetails>> response = new APIResponse<>(true, dataList, null);
            return new ResponseEntity<>(response, HttpStatus.OK);
        }catch (Exception e){
            APIResponse<List<OrderDetails>> response = new APIResponse<>(false, null, e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }
    }

}
