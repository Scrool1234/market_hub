package com.example.market_hub.admin.catalog;

import com.example.market_hub.catalog.dto.product.CreateProductRequest;
import com.example.market_hub.catalog.dto.product.ProductResponse;
import com.example.market_hub.catalog.dto.product.UpdateProductRequest;
import com.example.market_hub.catalog.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/v1/admin/products")
@RequiredArgsConstructor
public class AdminProductController {

    private final ProductService productService;

    @PostMapping
    public ResponseEntity<ProductResponse> create(@RequestBody @Valid CreateProductRequest createProductRequest) {
        return ResponseEntity.status(HttpStatus.CREATED).body(productService.create(createProductRequest));
    }

    @PatchMapping ("/{productId}")
    public ResponseEntity<ProductResponse> update(@PathVariable Long productId,
                                                  @RequestBody @Valid UpdateProductRequest updateProductRequest) {
        return ResponseEntity.ok(productService.updateProduct(productId, updateProductRequest));
    }

    @DeleteMapping("/{productId}")
    public ResponseEntity<Void> delete(@PathVariable Long productId) {
        productService.delete(productId);
        return ResponseEntity.noContent().build();
    }

}
