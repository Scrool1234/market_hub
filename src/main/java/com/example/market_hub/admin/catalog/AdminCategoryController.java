package com.example.market_hub.admin.catalog;

import com.example.market_hub.catalog.dto.category.CategoryResponse;
import com.example.market_hub.catalog.dto.category.CreateCategoryRequest;
import com.example.market_hub.catalog.dto.category.UpdateCategoryRequest;
import com.example.market_hub.catalog.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/v1/admin/categories")
@RequiredArgsConstructor
public class AdminCategoryController {

    private final CategoryService categoryService;

    @PostMapping
    public ResponseEntity<CategoryResponse> create(@RequestBody @Valid CreateCategoryRequest createCategoryRequest) {
        return ResponseEntity.status(HttpStatus.CREATED).body(categoryService.create(createCategoryRequest));
    }

    @PatchMapping("/{categoryId}")
    public ResponseEntity<CategoryResponse> update(@PathVariable Long categoryId,
                                                   @RequestBody @Valid UpdateCategoryRequest updateCategoryRequest) {
        return ResponseEntity.ok(categoryService.update(categoryId, updateCategoryRequest));
    }

    @DeleteMapping("/{categoryId}")
    public ResponseEntity<Void> delete(@PathVariable Long categoryId) {
        categoryService.delete(categoryId);
        return ResponseEntity.noContent().build();
    }
}
