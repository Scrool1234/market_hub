package com.example.market_hub.product.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public record UpdateProductDTO(
        @NotBlank String name,
        @Size(min = 35, max = 200) String description
) {
}
