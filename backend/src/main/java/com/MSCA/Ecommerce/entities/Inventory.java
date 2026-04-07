package com.MSCA.Ecommerce.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(
        name = "inventory",
        uniqueConstraints = {
                @UniqueConstraint(
                        name = "unique_product_warehouse",
                        columnNames = {"product_id", "warehouse_id"}
                )
        },
        indexes = {
//                @Index(name = "idx_inventory_product", -> no need of this
//                    columnList = "product_id"),
                @Index(name = "idx_inventory_warehouse",
                    columnList = "warehouse_id"),
                @Index(
                        name = "idx_inventory_product_update",
                        columnList = "product_id, last_stock_update"
                )
        }
)
public class Inventory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "inventory_id")
    private Long inventoryId;

    // product id foreign key //
    @ManyToOne
    @JoinColumn(name = "product_id", nullable = false)
    private Product product;

    // warehouse id foreign key//
    @ManyToOne
    @JoinColumn(name = "warehouse_id", nullable = false)
    private Warehouse warehouse;

    @Column(name = "on_hand_quantity", nullable = false)
    private Long onHandQuantity;

    @Column(name = "quantity_reserved")
    private Long quantityReserved;

    @Column(name = "last_stock_update")
    private LocalDateTime lastStockUpdate;

    @PreUpdate
    public void preUpdate() {
        lastStockUpdate = LocalDateTime.now();
    }
}
