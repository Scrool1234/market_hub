package com.example.market_hub.product.controller;

import com.example.market_hub.product.service.ProductService;
import com.example.market_hub.product.dto.CreateProductDTO;
import com.example.market_hub.product.dto.ProductDTO;
import com.example.market_hub.product.dto.UpdateProductDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/v1/products")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    @PostMapping
    public ResponseEntity<ProductDTO> create(@Valid @RequestBody CreateProductDTO createProductDTO) {
        return ResponseEntity.status(HttpStatus.CREATED).body(productService.create(createProductDTO));
    }

    @PatchMapping("/{productId}")
    public ResponseEntity<ProductDTO> update(@PathVariable Long productId,
                                             @Valid @RequestBody UpdateProductDTO updateProductDTO) {
        return ResponseEntity.ok(productService.update(productId, updateProductDTO));
    }

    @DeleteMapping("/{productId}")
    public ResponseEntity<Void> delete(@PathVariable Long productId) {
        productService.delete(productId);
        return ResponseEntity.noContent().build();
    }

    @GetMapping
    public ResponseEntity<List<ProductDTO>> getAll() {
        return ResponseEntity.ok(productService.getAll());
    }

    @GetMapping("/{productId}")
    public ResponseEntity<ProductDTO> getById(@PathVariable Long productId) {
        return ResponseEntity.ok(productService.getById(productId));
    }

}
