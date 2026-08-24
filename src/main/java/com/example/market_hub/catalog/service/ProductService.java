package com.example.market_hub.catalog.service;

import com.example.market_hub.catalog.entity.ProductVariant;
import com.example.market_hub.catalog.repository.specification.ProductSpecification;
import com.example.market_hub.catalog.repository.CategoryRepository;
import com.example.market_hub.catalog.mapper.ProductMapper;
import com.example.market_hub.catalog.repository.ProductRepository;
import com.example.market_hub.catalog.dto.product.CreateProductRequest;
import com.example.market_hub.catalog.dto.product.ProductResponse;
import com.example.market_hub.catalog.dto.product.UpdateProductRequest;
import com.example.market_hub.catalog.entity.Category;
import com.example.market_hub.catalog.entity.Product;
import com.example.market_hub.core.dto.PageResponse;
import com.example.market_hub.core.exception.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductMapper productMapper;
    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;

    @Transactional
    public ProductResponse create(CreateProductRequest createProductRequest) {

        Category category = categoryRepository.findById(createProductRequest.categoryId())
                .orElseThrow(() -> new ResourceNotFoundException(
                        String.format("Category with id %d not found", createProductRequest.categoryId())
                ));

        Product product = Product.builder()
                .name(createProductRequest.name())
                .description(createProductRequest.description())
                .price(createProductRequest.price())
                .category(category)
                .build();

        List<ProductVariant> productVariantList = createProductRequest.productVariantDTOList().stream()
                .map(x ->
                        ProductVariant.builder()
                            .color(x.color())
                            .size(x.size())
                            .product(product)
                            .build()
                )
                .toList();


        product.getProductVariants().addAll(productVariantList);
        Product savedProduct = productRepository.save(product);
        log.info("Product with id {} created successfully", savedProduct.getId());
        return productMapper.toProductResponse(savedProduct);
    }

    public ProductResponse updateProduct(Long productId, UpdateProductRequest updateProductRequest) {

        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new ResourceNotFoundException(
                        String.format("Product with id %d not found", productId)
                ));

        if(updateProductRequest.name() != null) {
            product.setName(updateProductRequest.name());
        }

        if(updateProductRequest.description() != null) {
            product.setDescription(updateProductRequest.description());
        }

        if(updateProductRequest.price() != null) {
            product.setPrice(updateProductRequest.price());
        }

        if(updateProductRequest.categoryId() != null) {
            Category category = categoryRepository.findById(updateProductRequest.categoryId())
                            .orElseThrow(() -> new ResourceNotFoundException(
                                    String.format("Category with id %d not found", updateProductRequest.categoryId())
                            ));
            product.setCategory(category);
        }

        Product updatedProduct = productRepository.save(product);
        log.info("Product with id {} full updated successfully", productId);
        return productMapper.toProductResponse(updatedProduct);
    }

    public void delete(Long productId) {
        Product product = productRepository.findById(productId)
                        .orElseThrow(() -> new ResourceNotFoundException(
                                String.format("Product with id %d not found", productId)
                        ));

        productRepository.delete(product);
        log.info("Product with id {} deleted successfully", productId);
    }

    public PageResponse<ProductResponse> getAll(Long categoryId,
                                                String color,
                                                BigDecimal minPrice,
                                                BigDecimal maxPrice,
                                                Pageable pageable) {

        Specification<Product> productSpecification = ProductSpecification.conjunction();

        if(categoryId != null) productSpecification = productSpecification.and(ProductSpecification.categoryId(categoryId));
        if(color != null) productSpecification = productSpecification.and(ProductSpecification.color(color));
        if(minPrice != null) productSpecification = productSpecification.and(ProductSpecification.minPrice(minPrice));
        if(maxPrice != null) productSpecification = productSpecification.and(ProductSpecification.maxPrice(maxPrice));

        Page<ProductResponse> page = productRepository.findAll(productSpecification, pageable).map(productMapper::toProductResponse);

         return PageResponse.<ProductResponse>builder()
                 .content(page.getContent())
                 .page(page.getNumber())
                 .size(page.getSize())
                 .totalPages(page.getTotalPages())
                 .totalElements(page.getTotalElements())
                 .build();
    }

    public ProductResponse getById(Long productId) {

        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new ResourceNotFoundException(
                        String.format("Product with id %d not found", productId)
                ));

        return productMapper.toProductResponse(product);
    }

}
