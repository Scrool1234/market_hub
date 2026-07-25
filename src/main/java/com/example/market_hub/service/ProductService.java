package com.example.market_hub.service;

import com.example.market_hub.dto.products.CreateProductDTO;
import com.example.market_hub.entity.Category;
import com.example.market_hub.entity.Product;
import com.example.market_hub.repository.CategoryRepository;
import com.example.market_hub.repository.ProductRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;

    public void getProducts() {

    }

    public void createProduct(CreateProductDTO createProductDTO) {

        Category category = categoryRepository.findById(createProductDTO.getCategoryId())
                .orElseThrow(() -> new EntityNotFoundException(
                        String.format("Category with id = %s not found", createProductDTO.getCategoryId())
                ));

        Product product = Product.builder()
                .name(createProductDTO.getName())
                .description(createProductDTO.getDescription())
                .category(category)
                .build();

        productRepository.save(product);

    }

    public void deleteProduct() {

    }

}
