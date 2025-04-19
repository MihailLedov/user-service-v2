package com.ledok.spring.security.userservice.service;

import com.ledok.spring.security.userservice.advice.UserNotFoundException;
import com.ledok.spring.security.userservice.feign.order.OrderClient;
import com.ledok.spring.security.userservice.feign.order.dto.CreateOrderRequest;
import com.ledok.spring.security.userservice.feign.order.dto.DeliveryDateRequest;
import com.ledok.spring.security.userservice.feign.order.dto.OrderDto;
import com.ledok.spring.security.userservice.feign.order.dto.OrderSummaryDto;
import com.ledok.spring.security.userservice.feign.product.dto.ProductDto;
import com.ledok.spring.security.userservice.jpa.entity.UserEntity;
import com.ledok.spring.security.userservice.jpa.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {
    private final OrderClient orderClient;
    private final UserRepository userRepository;

    @Override
    @Transactional
    public OrderDto createOrder(String username,CreateOrderRequest createOrderDto) {
       UserEntity user = userRepository.findByUsername(username).orElseThrow(()-> new UserNotFoundException("Пользователь не существует"));
       Long userId = user.getId();
           return orderClient.createOrder(userId,createOrderDto).getBody();
    }

    @Override
    @Transactional
    public OrderDto updateOrderDeliverDate(Long orderId, DeliveryDateRequest deliveryDateDto) {
        return orderClient.updateOrderDeliveryDate(orderId, deliveryDateDto).getBody();
    }

    @Override
    @Transactional
    public List<OrderDto> getAllOrdersByUserId(String username) {
        Optional <UserEntity> user = userRepository.findByUsername(username);
        Long userId = user.get().getId();
        return orderClient.getOrdersByUser(userId).getBody();
    }

    @Override
    @Transactional
    public List <ProductDto> getOrderedProductsByUser(String username) {
        Optional <UserEntity> user = userRepository.findByUsername(username);
        Long userId = user.get().getId();
        return orderClient.getOrderedProductsByUser(userId).getBody();
    }

    @Override
    @Transactional
    public OrderSummaryDto getOrderSummaryById(String username, String period) {
        Optional <UserEntity> user = userRepository.findByUsername(username);
        Long userId = user.get().getId();
        return orderClient.getOrderSummary(userId, period).getBody();
    }

    @Override
    public void cancelOrder(long id) {
        orderClient.cancelOrder(id);
    }
}
