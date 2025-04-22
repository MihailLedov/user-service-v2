package com.ledok.spring.security.userservice.gateway.order.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderItemDto {
    private Long productId;
    private Integer quantity;
    private BigDecimal price;
}
