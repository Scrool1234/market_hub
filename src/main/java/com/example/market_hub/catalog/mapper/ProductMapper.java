package com.example.market_hub.catalog.mapper;

import com.example.market_hub.catalog.dto.product.ProductResponse;
import com.example.market_hub.catalog.entity.Product;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ProductMapper {
    ProductResponse toProductResponse(Product product);
}
