package com.example.market_hub.product.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;


@Getter
@Setter
@Entity
@Builder
@Table(name = "products")
@NoArgsConstructor
@AllArgsConstructor
public class Product {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "description", nullable = false)
    private String description;

    @OneToMany(
            fetch = FetchType.LAZY,
            mappedBy = "product"
    )
    public List<ProductVariant> productVariants = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;

}
