package com.example.market_hub.catalog.dto.category;

import javax.validation.constraints.NotBlank;

public record UpdateCategoryRequest(

        @NotBlank
        String name,

        Long parentId
) {
}
