package com.example.market_hub.catalog.mapper;

import com.example.market_hub.catalog.dto.category.CategoryResponse;
import com.example.market_hub.catalog.entity.Category;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CategoryMapper {
    CategoryResponse toCategoryResponse(Category category);
}
