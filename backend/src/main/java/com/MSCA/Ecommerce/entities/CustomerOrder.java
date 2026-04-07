package com.MSCA.Ecommerce.entities;
import com.MSCA.Ecommerce.enums.*;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.aspectj.lang.annotation.DeclarePrecedence;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "customer_order",
       indexes = {
               @Index(
                       name = "idx_order_customer_date",
                       columnList = "customer_id, created_date"
               ),

               @Index(
                       name = "idx_order_customer_status_date",
                       columnList = "customer_id, order_status, updated_date"
               ),

               @Index(
                       name = "idx_order_date",
                       columnList = "created_date"
               )
       }
)
public class CustomerOrder {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_id")
    private Long orderId;

    @Column(name = "updated_date",  nullable = false)
    private LocalDateTime updatedDate;

    @Column(name = "created_date",  nullable = false)
    private LocalDateTime createdDate;

    @Enumerated(value = EnumType.STRING)
    @Column(name = "order_status", nullable = false, columnDefinition = "varchar(255) default 'CREATED'")
    private OrderStatus orderStatus;

    @Column(name = "total_amount")
    private Double total_amount;

//    @Column()

    @OneToMany(mappedBy = "order", cascade = CascadeType.PERSIST, orphanRemoval = true)
    private List<Invoice> invoiceList;

    // Customer Join //
    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;

    // order items join one to many//
    @OneToMany(mappedBy = "customerOrder", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<OrderItems> orderItems;

//    @PrePersist
    @PreUpdate
    public void preUpdate() {
        this.updatedDate = LocalDateTime.now();
    }

    @PrePersist
    public void prePersist() {
        this.createdDate = LocalDateTime.now();
        if(this.updatedDate == null){
            this.updatedDate = LocalDateTime.now();
        }
    }

    @Override
    public boolean equals(Object o){
        if(this == o) return true;
        if(!(o instanceof CustomerOrder)) return false;

        CustomerOrder other = (CustomerOrder) o;
        return (orderId != null && orderId.equals(other.orderId));
    }

    @Override
    public int hashCode(){
        return getClass().hashCode();
    }

    @Override
    public String toString(){
        return "orderId : "+orderId;
    }

    public List<OrderItems> getOrderItems(){
        if(this.orderItems == null){
            this.orderItems = new ArrayList<>();
        }
        return this.orderItems;
    }

}
