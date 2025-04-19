package com.ledok.spring.security.userservice.service;

import com.ledok.spring.security.userservice.feign.product.dto.ProductDto;
import com.ledok.spring.security.userservice.feign.product.dto.ProductFilter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ProductService {

    // Операции с продуктами
    List<ProductDto> getAllProducts();

    Page<ProductDto> gelAllProductsFilter(ProductFilter filter, Pageable pageable);
}
