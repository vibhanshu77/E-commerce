package com.MSCA.Ecommerce.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "warehouse")
public class Warehouse {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "warehouse_id")
    private Long WarehouseId;

    @Column(name = "warehouse_name", unique = true, nullable = false)
    private String WarehouseName;

    @Column(name = "address", nullable = false)
    private String Address;

    @Column(name = "warehouse_mail", nullable = false)
    private  String WarehouseMail;

    @Column(name = "warehouse_number", nullable = false)
    private String WarehouseNumber;

//    @Column(name = "warehouse_Manager", )

    @Column(name = "operating_hours", nullable = false)
    private String OperatingHours;

    // warehouse staff join//
    @OneToMany(mappedBy = "warehouse", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Employee> employees;

    // warehouse inventory join //
    @OneToMany(mappedBy = "warehouse", cascade = CascadeType.ALL)
    private List<Inventory> inventory;

}
