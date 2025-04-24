package com.ledok.spring.security.userservice.gateway.order.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateOrderRequest {

    @NotEmpty
    private List<OrderItemDto> items;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    private LocalDateTime deliveryDate;
}