package com.ledok.spring.security.userservice.service;

import com.ledok.spring.security.userservice.gateway.product.ProductClient;
import com.ledok.spring.security.userservice.gateway.product.dto.ProductDto;
import com.ledok.spring.security.userservice.gateway.product.dto.ProductFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {
    private final ProductClient productClient;

    @Override
    @Transactional(readOnly = true)
    public List<ProductDto> getAllProducts() {
        return productClient.getAllProduct().getBody();
    }

    @Override
    @Transactional
    public Page<ProductDto> gelAllProductsFilter(ProductFilter filter, Pageable pageable) {
        return productClient.getAllProductsFilter(filter, pageable).getBody();
    }
}
