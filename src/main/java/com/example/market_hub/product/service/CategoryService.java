package com.example.market_hub.product.service;

import com.example.market_hub.product.dto.CategoryDTO;
import com.example.market_hub.product.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryService {

    private final CategoryRepository categoryRepository;

    public List<CategoryDTO> getAll() {
        return null;
    }
}
