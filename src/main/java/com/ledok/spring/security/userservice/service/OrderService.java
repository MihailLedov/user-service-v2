package com.ledok.spring.security.userservice.service;

import com.ledok.spring.security.userservice.feign.order.dto.CreateOrderRequest;
import com.ledok.spring.security.userservice.feign.order.dto.DeliveryDateRequest;
import com.ledok.spring.security.userservice.feign.order.dto.OrderDto;
import com.ledok.spring.security.userservice.feign.order.dto.OrderSummaryDto;
import com.ledok.spring.security.userservice.feign.product.dto.ProductDto;

import java.util.List;

public interface OrderService {
    // Операции с заказами
    OrderDto createOrder(String username,CreateOrderRequest createOrderDto);

    OrderDto updateOrderDeliverDate(Long orderId, DeliveryDateRequest deliveryDateDto);

    List<OrderDto> getAllOrdersByUserId(String username);

    List<ProductDto> getOrderedProductsByUser(String username);

    OrderSummaryDto getOrderSummaryById(String username, String period);

    void cancelOrder(long id);

}
