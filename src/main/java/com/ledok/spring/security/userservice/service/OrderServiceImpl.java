package com.ledok.spring.security.userservice.service;

import com.ledok.spring.security.userservice.advice.UserNotFoundException;
import com.ledok.spring.security.userservice.gateway.order.OrderClient;
import com.ledok.spring.security.userservice.gateway.order.dto.CreateOrderRequest;
import com.ledok.spring.security.userservice.gateway.order.dto.DeliveryDateRequest;
import com.ledok.spring.security.userservice.gateway.order.dto.OrderDto;
import com.ledok.spring.security.userservice.gateway.order.dto.OrderSummaryDto;
import com.ledok.spring.security.userservice.gateway.product.dto.ProductDto;
import com.ledok.spring.security.userservice.persistence.entity.UserEntity;
import com.ledok.spring.security.userservice.persistence.repository.UserRepository;
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
    private final UserAuthentication userAuthentication;

    @Override
    @Transactional
    public OrderDto createOrder(CreateOrderRequest createOrderDto) {
        String username = userAuthentication.getUsername();
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
    public List<OrderDto> getAllOrdersByUserId() {
        String username = userAuthentication.getUsername();
        Optional <UserEntity> user = userRepository.findByUsername(username);
        Long userId = user.get().getId();
        return orderClient.getOrdersByUser(userId).getBody();
    }

    @Override
    @Transactional
    public List <ProductDto> getOrderedProductsByUser() {
        String username = userAuthentication.getUsername();
        Optional <UserEntity> user = userRepository.findByUsername(username);
        Long userId = user.get().getId();
        return orderClient.getOrderedProductsByUser(userId).getBody();
    }

    @Override
    @Transactional
    public OrderSummaryDto getOrderSummaryById(String period) {
        String username = userAuthentication.getUsername();
        Optional <UserEntity> user = userRepository.findByUsername(username);
        Long userId = user.get().getId();
        return orderClient.getOrderSummary(userId, period).getBody();
    }

    @Override
    public void cancelOrder(long id) {
        orderClient.cancelOrder(id);
    }
}
