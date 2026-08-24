package com.example.market_hub.catalog.service;

import com.example.market_hub.catalog.dto.category.CategoryResponse;
import com.example.market_hub.catalog.dto.category.CreateCategoryRequest;
import com.example.market_hub.catalog.dto.category.UpdateCategoryRequest;
import com.example.market_hub.catalog.entity.Category;
import com.example.market_hub.catalog.mapper.CategoryMapper;
import com.example.market_hub.catalog.repository.CategoryRepository;
import com.example.market_hub.core.exception.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class CategoryService {

    private final CategoryMapper categoryMapper;
    private final CategoryRepository categoryRepository;

    public CategoryResponse create(CreateCategoryRequest createCategoryRequest) {

        Category category = Category.builder()
                .name(createCategoryRequest.name())
                .build();

        Category savedCategory = categoryRepository.save(category);
        log.info("Category with id {} created successfully", savedCategory.getId());
        return categoryMapper.toCategoryResponse(savedCategory);

    }

    public CategoryResponse update(Long categoryId, UpdateCategoryRequest updateCategoryRequest) {

        Category category = categoryRepository.findById(categoryId)
                .orElseThrow(() -> new ResourceNotFoundException(
                        String.format("Category variant with id %d not found", categoryId)
                ));

        if(updateCategoryRequest.name() != null) {
            category.setName(updateCategoryRequest.name());
        }

        if(updateCategoryRequest.parentId() != null) {
            Category parentCategory = categoryRepository.findById(updateCategoryRequest.parentId())
                    .orElseThrow(() -> new ResourceNotFoundException(
                            String.format("Parent category with id %d not found", updateCategoryRequest.parentId())
                    ));
            category.setCategory(parentCategory);
        }

        Category updatedCategory = categoryRepository.save(category);
        log.info("Category with id {} updated successfully", updatedCategory.getId());
        return categoryMapper.toCategoryResponse(updatedCategory);
    }

    public void delete(Long categoryId) {

        Category category = categoryRepository.findById(categoryId)
                .orElseThrow(() -> new ResourceNotFoundException(
                        String.format("Category with id %d not found", categoryId)
                ));

        categoryRepository.delete(category);
        log.info("Category with id {} deleted successfully", categoryId);
    }

    public List<CategoryResponse> getAll() {
        return categoryRepository.findAll().stream()
                .map(categoryMapper::toCategoryResponse)
                .toList();
    }
}
