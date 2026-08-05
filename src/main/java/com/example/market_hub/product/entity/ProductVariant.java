package com.example.market_hub.product.entity;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@Entity
@Builder
@Table(
        name = "product_variants",
        uniqueConstraints = @UniqueConstraint(
                columnNames = {"product_id"}
        )
)
@NoArgsConstructor
@AllArgsConstructor
public class ProductVariant {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "size", nullable = false)
    private String size;

    @Column(name = "color", nullable = false)
    private String color;

    @Column(name = "price", nullable = false)
    private BigDecimal price;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

}
