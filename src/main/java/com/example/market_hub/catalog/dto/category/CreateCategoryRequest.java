package com.example.market_hub.catalog.dto.category;

import javax.validation.constraints.NotBlank;

public record CreateCategoryRequest(
        @NotBlank String name
) {
}
