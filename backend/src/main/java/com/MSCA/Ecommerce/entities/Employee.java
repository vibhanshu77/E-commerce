package com.MSCA.Ecommerce.entities;


import com.MSCA.Ecommerce.enums.*;
//import com.MSCA.Ecommerce.enums.Role;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "users",

        indexes = {@Index(name = "idx_uearname_accountstatus",
                    columnList = "user_name, account_status"
                    ),
                    @Index(name = "idx_created_at",
                            columnList = "account_status, created_at"
                    ),
                    @Index(name = "idx_user_warehouse",
                            columnList = "warehouse_id"
                    )
                    }
        )
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="user_id")
    private Long id;

//    @Column(nullable = false, unique = true, length = 36)
//    private String uuid;

    @Column(nullable = false, unique = true, length = 100, name = "user_name")
    private String username;

    @Column(nullable = false, unique = true, length = 150, name = "email_id")
    private String email;

    @Column(nullable = false, name = "password")
    private String password;

//    @Column(nullable = false, length = 20, name = "status")
//    private String status;

    @Column(name = "is_email_verified")
    private boolean emailVerified;

    @Enumerated(EnumType.STRING)
    @Column(name = "account_status")
    private AccountStatus accountStatus;

    @Column(name = "password_status")
    private boolean isPasswordExpired;

    @Column(updatable = false, name = "created_at")
    private LocalDateTime createdAt;

    @Column(name = "updated_date", updatable = true)
    private LocalDateTime updatedAt;

    // NEVER EAGER FETCH AT SCALE
//    @ManyToMany(fetch = FetchType.LAZY)
//    @JoinTable(
//            name = "user_roles",
//            joinColumns = @JoinColumn(name = "user_id"),
//            inverseJoinColumns = @JoinColumn(name = "role_id")
//    )
    // Go with enum approach //
    @Enumerated(EnumType.STRING)
    @Column(name = "user_role", nullable = false)
    private Set<Role> roles = new HashSet<>();

    // warehouse user Join //
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "warehouse_id")
    private Warehouse warehouse;

    @PrePersist
    void onCreate() {
        createdAt = LocalDateTime.now();
        //uuid = UUID.randomUUID().toString();
    }

    @PreUpdate
    void onUpdate() {
        updatedAt = LocalDateTime.now();
    }

    // getters & setters
}
