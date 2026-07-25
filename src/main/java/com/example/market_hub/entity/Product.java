package com.example.market_hub.entity;

import jakarta.persistence.*;
import lombok.*;


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

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;

}
