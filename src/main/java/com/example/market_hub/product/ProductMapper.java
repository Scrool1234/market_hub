package com.example.market_hub.product;

import com.example.market_hub.product.dto.ProductDTO;
import com.example.market_hub.product.entity.Product;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ProductMapper {

    ProductDTO toProductDTO(Product product);

}
