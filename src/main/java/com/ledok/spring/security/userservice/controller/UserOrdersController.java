package com.ledok.spring.security.userservice.controller;


import com.ledok.spring.security.userservice.feign.order.dto.CreateOrderRequest;
import com.ledok.spring.security.userservice.feign.order.dto.DeliveryDateRequest;
import com.ledok.spring.security.userservice.feign.order.dto.OrderDto;
import com.ledok.spring.security.userservice.feign.order.dto.OrderSummaryDto;
import com.ledok.spring.security.userservice.feign.product.dto.ProductDto;
import com.ledok.spring.security.userservice.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/shopping/orders")
@RequiredArgsConstructor
public class UserOrdersController {

    private final OrderService orderService;

    @PostMapping
    public ResponseEntity<OrderDto> createOrder(@RequestBody CreateOrderRequest createOrderDto) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        return ResponseEntity.ok(orderService.createOrder(username,createOrderDto));
    }

    @PutMapping("/delivery/{orderId}/change")
    public ResponseEntity<OrderDto> updateOrderDeliveryDate(
            @PathVariable Long orderId,
            @RequestBody DeliveryDateRequest deliveryDateRequest) {
        return ResponseEntity.ok(orderService.updateOrderDeliverDate(orderId,deliveryDateRequest));
    }

    @GetMapping("/all")
    public ResponseEntity<List<OrderDto>> getAllOrders() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
       return ResponseEntity.ok(orderService.getAllOrdersByUserId(username));
    }

    @PostMapping("/{orderId}/cancel")
    public ResponseEntity<OrderDto> cancelOrder(@PathVariable Long orderId){
        orderService.cancelOrder(orderId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/summary")
    public ResponseEntity<OrderSummaryDto> getOrderSummary(@RequestParam String period) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        return ResponseEntity.ok(orderService.getOrderSummaryById(username, period));
    }

    @GetMapping("/products")
    public ResponseEntity<List<ProductDto>> getOrderedProductsByUser(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        return ResponseEntity.ok(orderService.getOrderedProductsByUser(username));
    }
}
