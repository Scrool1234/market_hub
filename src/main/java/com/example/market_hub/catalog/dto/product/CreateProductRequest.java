package com.example.market_hub.catalog.dto.product;

import com.example.market_hub.catalog.dto.productVariant.CreateProductVariantRequest;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.math.BigDecimal;
import java.util.List;

public record CreateProductRequest(

        @Size(max = 30)
        @NotBlank(message = "Название продукта не должно быть пустым")
        String name,

        @Size(max = 255)
        String description,

        Long categoryId,

        @Min(1)
        @NotNull
        BigDecimal price,

        List<CreateProductVariantRequest> productVariantDTOList

) {

}
