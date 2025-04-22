package com.ledok.spring.security.userservice.gateway.order;

import com.ledok.spring.security.userservice.gateway.order.dto.CreateOrderRequest;
import com.ledok.spring.security.userservice.gateway.order.dto.DeliveryDateRequest;
import com.ledok.spring.security.userservice.gateway.order.dto.OrderDto;
import com.ledok.spring.security.userservice.gateway.order.dto.OrderSummaryDto;
import com.ledok.spring.security.userservice.gateway.product.dto.ProductDto;
import jakarta.validation.Valid;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(name = "order-service", url = "http://localhost:8083")
public interface OrderClient {

    @PostMapping("/api/orders")
    public ResponseEntity<OrderDto> createOrder(@RequestParam Long userId, @RequestBody @Valid CreateOrderRequest request);

    @GetMapping("/api/orders/{userId}/all")
    public ResponseEntity<List<OrderDto>> getOrdersByUser(@PathVariable Long userId);

    @PostMapping("/api/orders/{orderId}/cancel")
    public ResponseEntity<OrderDto> cancelOrder(@PathVariable Long orderId);

    @PutMapping("/api/orders/delivery/{orderId}/change")
    public ResponseEntity<OrderDto> updateOrderDeliveryDate(
            @PathVariable Long orderId,
            @RequestBody DeliveryDateRequest deliveryDateRequest);

    @GetMapping("/api/orders/{userId}/summary")
    public ResponseEntity<OrderSummaryDto> getOrderSummary(
            @PathVariable Long userId,
            @RequestParam String period);

    @GetMapping("/api/orders/{userId}/products")
    public ResponseEntity<List<ProductDto>> getOrderedProductsByUser(@PathVariable Long userId);
}
