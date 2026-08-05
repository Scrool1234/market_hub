package com.example.market_hub.product.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public record CreateProductDTO(
        @NotBlank(message = "Имя продукта не должно быть пустым") String name,
        @Size(min = 35, max = 200) String description,
        Long categoryId) {

}
