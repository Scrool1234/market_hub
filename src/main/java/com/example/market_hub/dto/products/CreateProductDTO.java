package com.example.market_hub.dto.products;

import lombok.Data;

@Data
public class CreateProductDTO {
    private String name;
    private String description;
    private Long categoryId;
}
