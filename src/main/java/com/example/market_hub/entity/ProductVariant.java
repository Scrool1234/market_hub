package com.example.market_hub.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@Entity
@Table(
        name = "product_variants",
        uniqueConstraints = @UniqueConstraint(
                columnNames = {"sku", "price", "product_id"}
        )
)
public class ProductVariant {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "price", nullable = false)
    private BigDecimal price;

    @ManyToOne
    private A

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

}
