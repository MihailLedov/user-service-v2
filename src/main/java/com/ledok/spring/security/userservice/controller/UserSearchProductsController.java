package com.ledok.spring.security.userservice.controller;


import com.ledok.spring.security.userservice.feign.product.dto.ProductDto;
import com.ledok.spring.security.userservice.feign.product.dto.ProductFilter;
import com.ledok.spring.security.userservice.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/shopping/products")
@RequiredArgsConstructor
public class UserSearchProductsController {

    private final ProductService productService;

    @GetMapping
    public ResponseEntity<List<ProductDto>> getAllProduct() {
        return ResponseEntity.ok(productService.getAllProducts());
    }

    @PostMapping("/filter")
    public ResponseEntity<Page<ProductDto>> getAllProductsFilter(@ModelAttribute ProductFilter filter, Pageable pageable) {
        return ResponseEntity.ok(productService.gelAllProductsFilter(filter, pageable));
    }
}
