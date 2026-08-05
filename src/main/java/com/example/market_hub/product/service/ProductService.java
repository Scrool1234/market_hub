package com.example.market_hub.product.service;

import com.example.market_hub.product.repository.CategoryRepository;
import com.example.market_hub.product.ProductMapper;
import com.example.market_hub.product.repository.ProductRepository;
import com.example.market_hub.product.dto.CreateProductDTO;
import com.example.market_hub.product.dto.ProductDTO;
import com.example.market_hub.product.dto.UpdateProductDTO;
import com.example.market_hub.product.entity.Category;
import com.example.market_hub.product.entity.Product;
import com.example.market_hub.exception.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductMapper productMapper;
    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;

    public ProductDTO create(CreateProductDTO createProductDTO) {

        Category category = categoryRepository.findById(createProductDTO.categoryId())
                .orElseThrow(() -> new ResourceNotFoundException(
                        String.format("Category with id = %s not found", createProductDTO.categoryId())
                ));

        Product product = Product.builder()
                .name(createProductDTO.name())
                .description(createProductDTO.description())
                .category(category)
                .build();

        Product savedProduct = productRepository.save(product);
        log.info("Product with id {} created successfully", savedProduct.getId());

    }

    public ProductDTO update(Long productId, UpdateProductDTO updateProductDTO) {

        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new ResourceNotFoundException("Product not found"));

        if(updateProductDTO.name() != null) {

        }

        if(updateProductDTO.description() != null) {

        }

        Product savedProduct = productRepository.save(product);
        log.info("Product with id {} updated successfully", productId);
        return productMapper.toProductDTO(savedProduct);
    }

    public void delete(Long productId) {
        Product product = productRepository.findById(productId)
                        .orElseThrow(() -> new ResourceNotFoundException("Product not found"));

        productRepository.delete(product);
        log.info("Product with id {} deleted successfully", productId);
    }

    public List<ProductDTO> getAll() {
        return productRepository.findAll().stream()
                .map(productMapper::toProductDTO)
                .collect(Collectors.toList());
    }

    public ProductDTO getById(Long productId) {

        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new ResourceNotFoundException("Product not found"));

        return productMapper.toProductDTO(product);
    }

}
