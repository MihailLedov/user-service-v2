package com.ledok.spring.security.userservice.service;

import com.ledok.spring.security.userservice.gateway.order.dto.CreateOrderRequest;
import com.ledok.spring.security.userservice.gateway.order.dto.DeliveryDateRequest;
import com.ledok.spring.security.userservice.gateway.order.dto.OrderDto;
import com.ledok.spring.security.userservice.gateway.order.dto.OrderSummaryDto;
import com.ledok.spring.security.userservice.gateway.product.dto.ProductDto;

import java.util.List;

public interface OrderService {
    // Операции с заказами
    OrderDto createOrder(CreateOrderRequest createOrderDto);

    OrderDto updateOrderDeliverDate(Long orderId, DeliveryDateRequest deliveryDateDto);

    List<OrderDto> getAllOrdersByUserId();

    List<ProductDto> getOrderedProductsByUser();

    OrderSummaryDto getOrderSummaryById(String period);

    void cancelOrder(long id);

}
