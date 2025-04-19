package com.ledok.spring.security.userservice.feign.order.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderSummaryDto {
    private LocalDate startDate;
    private LocalDate endDate;
    private BigDecimal totalAmount;
    private int orderCount;
}
