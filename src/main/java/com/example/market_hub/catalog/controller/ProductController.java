package com.example.market_hub.catalog.controller;

import com.example.market_hub.catalog.service.ProductService;
import com.example.market_hub.catalog.dto.product.ProductResponse;
import com.example.market_hub.core.dto.PageResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

@RestController
@RequestMapping("/api/v1/products")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    @GetMapping
    public ResponseEntity<PageResponse<ProductResponse>> getAll(@RequestParam(required = false) Long categoryId,
                                                                @RequestParam(required = false) String color,
                                                                @RequestParam(required = false) BigDecimal minPrice,
                                                                @RequestParam(required = false) BigDecimal maxPrice,
                                                                Pageable pageable) {

        return ResponseEntity.ok(productService.getAll(
                categoryId,
                color,
                minPrice,
                maxPrice,
                pageable
        ));
    }

    @GetMapping("/{productId}")
    public ResponseEntity<ProductResponse> getById(@PathVariable Long productId) {
        return ResponseEntity.ok(productService.getById(productId));
    }

}
