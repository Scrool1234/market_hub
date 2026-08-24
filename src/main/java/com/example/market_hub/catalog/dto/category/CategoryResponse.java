package com.example.market_hub.catalog.dto.category;

import com.example.market_hub.catalog.entity.Category;
import lombok.Builder;

@Builder
public record CategoryResponse(
        Long id,
        String name,
        Category parent
) {
}
