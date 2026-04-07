package com.MSCA.Ecommerce.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.engine.internal.Cascade;
import org.springframework.security.config.annotation.web.oauth2.login.OAuth2LoginSecurityMarker;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(
        name = "product",
        indexes = {
                // Product search
                @Index(
                        name = "idx_product_name",
                        columnList = "product_name"
                ),

                // Product listing (active + latest)
                @Index(
                        name = "idx_product_status_created",
                        columnList = "product_status, created_date"
                ),

                // Admin reports
                @Index(
                        name = "idx_product_created",
                        columnList = "created_date"
                )
        }
)
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "product_id")
    private int productId;

//    @Column(name = "prduct_code", unique = true, nullable = false)

    @Column(name = "product_name", nullable = false)
    private String productName;

    @Column(name = "product_description")
    private String productDescription;

//    @Column(name = "product_category") join column

    // Join with inventory //
    @JsonIgnore
    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Inventory> inventory;

    // join with order items //
    @JsonIgnore
    @OneToMany(mappedBy = "product")
    private List<OrderItems> orderItems;

    @Column(name = "product_price")
    private double productPrice;

    @Column(name = "product_status")
    private String productStatus;

    @Column(name = "created_date", nullable = false)
    private LocalDateTime createdDate;

    @Column(name = "updated_date", nullable = false)
    private LocalDateTime updatedDate;


}
