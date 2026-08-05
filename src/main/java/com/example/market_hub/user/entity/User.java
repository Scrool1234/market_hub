package com.example.market_hub.user.entity;

import com.example.market_hub.cart.entity.Cart;
import com.example.market_hub.order.entity.Order;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
@Builder
@Table(name = "users")
@NoArgsConstructor
@AllArgsConstructor
public class User {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "first_name", nullable = false)
    private String firstName;

    @Column(name = "last_name", nullable = false)
    private String lastName;

    @Column(name = "email", nullable = false, unique = true)
    private String email;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "phone", unique = true)
    private String phone;

    @CreationTimestamp
    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at", nullable = false)
    private LocalDateTime updatedAt;

    @ManyToOne
    @JoinColumn(name = "role_id")
    private Role role;

    @OneToMany(
            fetch = FetchType.LAZY,
            mappedBy = "user"
    )
    @Builder.Default
    private List<Order> orders = new ArrayList<>();

    @OneToOne(
            cascade = CascadeType.ALL
    )
    @JoinColumn(name = "cart_id")
    @Builder.Default
    private Cart cart;

    @OneToOne(
            cascade = CascadeType.ALL
    )
    @JoinColumn(name = "address_id")
    @Builder.Default
    private Address address;


}
