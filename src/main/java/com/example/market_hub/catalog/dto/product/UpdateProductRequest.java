package com.example.market_hub.catalog.dto.product;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.math.BigDecimal;

public record UpdateProductRequest(

        @NotBlank
        String name,

        @Size(max = 255)
        String description,

        @Min(1)
        @NotNull
        BigDecimal price,

        Long categoryId

) {
}
