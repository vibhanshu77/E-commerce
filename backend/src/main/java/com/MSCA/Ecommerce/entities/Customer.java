package com.MSCA.Ecommerce.entities;


import com.MSCA.Ecommerce.enums.AccountStatus;
import com.MSCA.Ecommerce.enums.Role;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "customer",
    indexes = {
            @Index(name = "idx_customer_email_active",
                    columnList = "customer_email, is_account_active"),

            @Index(name = "idx_customer_active_created",
                    columnList = "is_account_active, created_on"),

            @Index(name = "idx_customer_active_created",
                    columnList = "is_account_active, created_on")
    }
)
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "customer_id",  nullable = false)
    private Long customerId;

    @Column(name = "customer_name", unique = true, nullable = false)
    private String customerName;

    @Column(name = "customer_email", unique = true, nullable = false)
    private String customerEmail;

    @Column(name = "customer_password", nullable = false)
    private String customerPassword;

    @Column(name = "contact_number", nullable = false)
    private String contactNumber;

    @Column(name = "shipping_adderss", nullable = false)
    private String shippingAdderss;

    @Column(name = "billing_address", nullable = false)
    private String billingAddress;

    @Column(name = "is_account_active", nullable = false)
    private Boolean isAccountActive;

    @Column(name = "is_password_expired", nullable = false)
    private Boolean isPasswordExpired;

    @Column(name = "created_on", nullable = false)
    private LocalDateTime createdOn;

    @Column(name = "last_login", nullable = false)
    private LocalDateTime lastLogin;

    // orders mapping //
    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<CustomerOrder> customerOrders;

    // invoice mapping //
    @OneToMany(mappedBy = "customerId", cascade = CascadeType.PERSIST, orphanRemoval = true)
    private List<Invoice> invoiceList;

    @Enumerated(EnumType.STRING)
    @Column(name = "account_status", nullable = false, columnDefinition = "varchar(255) default 'ACTIVE'")
    private AccountStatus accountStatus;

    @Enumerated(EnumType.STRING)
    @Column(name = "customer_role", nullable = false, columnDefinition = "varchar(255) default 'ROLE_CUSTOMER'")
    private Role customerRole;

    @PrePersist
    public void createdOn() {
        createdOn = LocalDateTime.now();
    }

    @PreUpdate
    public void updatedOn() {
        lastLogin = LocalDateTime.now();
    }

    @Override
    public boolean equals(Object o){
        if(this == o) return true;
        if(!(o instanceof Customer)) return false;

        Customer other = (Customer) o;
        return (customerId != null && customerId.equals(other.customerId));
    }

    @Override
    public int hashCode(){
        return getClass().hashCode();
    }

    @Override
    public String toString(){
        return "CustomerId : "+customerId;
    }

    public String getCustomerName(){
        return this.customerName;
    }

    //public Role getCustomerRole(){return this.customerRole;}

    public Set<CustomerOrder> getCustomerOrders(){
        return this.customerOrders;
    }

    public Role getCustomerRole(){
        return this.customerRole;
    }
}
