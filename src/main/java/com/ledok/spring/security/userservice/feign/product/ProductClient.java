package com.ledok.spring.security.userservice.feign.product;

import com.ledok.spring.security.userservice.feign.product.dto.ProductDto;
import com.ledok.spring.security.userservice.feign.product.dto.ProductFilter;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@FeignClient(name = "product-service",url = "http://localhost:8082")
public interface ProductClient {

    @GetMapping("/api/products")
    public ResponseEntity<List<ProductDto>> getAllProduct ();

    @PostMapping("/api/products/filter")
    public ResponseEntity<Page <ProductDto>> getAllProductsFilter(@ModelAttribute ProductFilter filter, Pageable pageable);
}
