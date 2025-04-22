package com.ledok.spring.security.userservice.controller;


import com.ledok.spring.security.userservice.gateway.order.dto.CreateOrderRequest;
import com.ledok.spring.security.userservice.gateway.order.dto.DeliveryDateRequest;
import com.ledok.spring.security.userservice.gateway.order.dto.OrderDto;
import com.ledok.spring.security.userservice.gateway.order.dto.OrderSummaryDto;
import com.ledok.spring.security.userservice.gateway.product.dto.ProductDto;
import com.ledok.spring.security.userservice.service.OrderService;
import jakarta.validation.Valid;
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
    public ResponseEntity<OrderDto> createOrder(@RequestBody @Valid CreateOrderRequest createOrderDto) {
        return ResponseEntity.ok(orderService.createOrder(createOrderDto));
    }

    @PutMapping("/delivery/{orderId}/change")
    public ResponseEntity<OrderDto> updateOrderDeliveryDate(
            @PathVariable Long orderId,
            @RequestBody DeliveryDateRequest deliveryDateRequest) {
        return ResponseEntity.ok(orderService.updateOrderDeliverDate(orderId,deliveryDateRequest));
    }

    @GetMapping("/all")
    public ResponseEntity<List<OrderDto>> getAllOrders() {
       return ResponseEntity.ok(orderService.getAllOrdersByUserId());
    }

    @PostMapping("/{orderId}/cancel")
    public ResponseEntity<OrderDto> cancelOrder(@PathVariable Long orderId){
        orderService.cancelOrder(orderId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/summary")
    public ResponseEntity<OrderSummaryDto> getOrderSummary(@RequestParam String period) {

        return ResponseEntity.ok(orderService.getOrderSummaryById(period));
    }

    @GetMapping("/products")
    public ResponseEntity<List<ProductDto>> getOrderedProductsByUser(){

        return ResponseEntity.ok(orderService.getOrderedProductsByUser());
    }
}
