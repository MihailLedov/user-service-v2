package com.ledok.spring.security.userservice.gateway.product.dto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class ProductFilter {
    private String name;
    private String category;
    private BigDecimal minPrice;
    private BigDecimal maxPrice;
}