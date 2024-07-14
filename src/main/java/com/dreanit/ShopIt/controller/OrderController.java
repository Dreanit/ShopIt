package com.dreanit.ShopIt.controller;

import com.dreanit.ShopIt.enums.OrderStatus;
import com.dreanit.ShopIt.model.APIResponse;
import com.dreanit.ShopIt.model.entityModel.Order;
import com.dreanit.ShopIt.model.entityModel.OrderItem;
import com.dreanit.ShopIt.model.entityModel.Product;
import com.dreanit.ShopIt.service.OrderService;
import com.dreanit.ShopIt.service.ProductService;
import com.dreanit.ShopIt.model.wrapper.OrderDetails;
import org.springframework.beans.factory.annotation.Autowired;
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
    public ResponseEntity<APIResponse<Order>> createProduct(@RequestBody Order order) {
        try {

            Order data = orderService.saveOrder(order);
            APIResponse<Order> response = new APIResponse<>(true, data, "Order Created Successfully");
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            APIResponse<Order> response = new APIResponse<>(false, null, e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/all")
    public ResponseEntity<APIResponse<List<OrderDetails>>> getAllOrders() {
        try {
            List<Order> data = orderService.getAllOrders();
            List<OrderDetails> dataList = data.stream().map(order -> {
                List<OrderDetails.OrderDetailItem> items = orderService.getAllItemOfOrder(order.id).stream()
                        .map(orderItem -> {
                            Product product = productService.getProductById(orderItem.getProductId());
                            return new OrderDetails.OrderDetailItem(
                                    orderItem.id,
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
        } catch (Exception e) {
            APIResponse<List<OrderDetails>> response = new APIResponse<>(false, null, e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/{orderId}")
    public ResponseEntity<APIResponse<OrderDetails>> getOrderById(@PathVariable UUID orderId) {
        try {
            Order order = orderService.getOrderById(orderId);
            System.out.println(order.id);

            if (order == null) {
                throw new Exception("Order not found");
            }
            List<OrderDetails.OrderDetailItem> items = orderService.getAllItemOfOrder(order.id).stream()
                    .map(orderItem -> {
                        Product product = productService.getProductById(orderItem.getProductId());
                        return new OrderDetails.OrderDetailItem(
                                orderItem.id, orderItem.getProductId(),
                                product.name,
                                product.status,
                                orderItem.getQuantity(),
                                orderItem.getPrice()
                        );
                    })
                    .collect(Collectors.toList());

            OrderDetails orderDetails = new OrderDetails(
                    order.id,
                    order.customerId,
                    order.orderDate,
                    order.status,
                    items
            );

            APIResponse<OrderDetails> response = new APIResponse<>(true, orderDetails, null);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            APIResponse<OrderDetails> response = new APIResponse<>(false, null, e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }
    }


    @PutMapping("/updateStatus/{orderId}")
    public ResponseEntity<APIResponse<Order>> updateOrderStatus(@PathVariable UUID orderId, @RequestParam OrderStatus status) {
        try {
            Order order = orderService.updateOrderStatus(orderId, status);
            APIResponse<Order> response = new APIResponse<>(true, order, "Order status updated successfully");
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            APIResponse<Order> response = new APIResponse<>(false, null, e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/addItem/{orderId}")
    public ResponseEntity<APIResponse<OrderDetails>> addItemToOrder(@PathVariable UUID orderId, @RequestBody OrderItem orderItem) {
        try {
            Order order = orderService.addItemToOrder(orderId, orderItem);
            List<OrderDetails.OrderDetailItem> items = orderService.getAllItemOfOrder(order.id).stream()
                    .map(item -> {
                        Product product = productService.getProductById(item.getProductId());
                        return new OrderDetails.OrderDetailItem(
                                item.id,
                                item.getProductId(),
                                product.name,
                                product.status,
                                item.getQuantity(),
                                item.getPrice()
                        );
                    })
                    .collect(Collectors.toList());

            OrderDetails orderDetails = new OrderDetails(
                    order.id,
                    order.customerId,
                    order.orderDate,
                    order.status,
                    items
            );

            APIResponse<OrderDetails> response = new APIResponse<>(true, orderDetails, "Item added to order successfully");
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            APIResponse<OrderDetails> response = new APIResponse<>(false, null, e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/removeItem/{orderId}/{itemId}")
    public ResponseEntity<APIResponse<OrderDetails>> removeItemFromOrder(@PathVariable UUID orderId, @PathVariable UUID itemId) {
        try {
            Order order = orderService.removeItemFromOrder(orderId, itemId);
            List<OrderDetails.OrderDetailItem> items = orderService.getAllItemOfOrder(order.id).stream()
                    .map(item -> {
                        Product product = productService.getProductById(item.getProductId());
                        return new OrderDetails.OrderDetailItem(
                                item.id,
                                item.getProductId(),
                                product.name,
                                product.status,
                                item.getQuantity(),
                                item.getPrice()
                        );
                    })
                    .collect(Collectors.toList());

            OrderDetails orderDetails = new OrderDetails(
                    order.id,
                    order.customerId,
                    order.orderDate,
                    order.status,
                    items
            );

            APIResponse<OrderDetails> response = new APIResponse<>(true, orderDetails, "Item removed from order successfully");
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            APIResponse<OrderDetails> response = new APIResponse<>(false, null, e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }
    }

}
