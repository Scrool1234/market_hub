package com.example.market_hub.catalog.dto.product;

import com.example.market_hub.catalog.dto.category.CategoryResponse;
import com.example.market_hub.catalog.dto.productVariant.ProductVariantResponse;
import lombok.Builder;

import java.math.BigDecimal;
import java.util.List;

@Builder
public record ProductResponse(
        String name,
        String description,
        BigDecimal price,
        List<ProductVariantResponse> productVariantDTOList,
        CategoryResponse category
) {
}
