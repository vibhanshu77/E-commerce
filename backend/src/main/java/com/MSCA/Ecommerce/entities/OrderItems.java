package com.MSCA.Ecommerce.entities;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "order_items",
       uniqueConstraints = {
               @UniqueConstraint(name = "UQ_order_product",
                       columnNames = {"order_id", "product_id"})
       },
       indexes = {
            @Index(name = "idx_orderItems_product",
                columnList = "product_id")
       }
)
public class OrderItems {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long orderItemId;

    // Many to one -> join with order//
    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "order_id")
    private CustomerOrder customerOrder;

    // Product join //
    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

    @Column(name = "Quantity")
    private Integer quantity;

    @Column(name = "unit_price")
    private Double unitPrice;

    @Column(name = "discount")
    private Double discount;

    @Column(name = "total_price")
    private Double totalPrice;

    @Override
    public boolean equals(Object o){
        if(this == o) return true;
        if(!(o instanceof OrderItems)) return false;

        OrderItems other = (OrderItems) o;
        return (orderItemId != null && orderItemId.equals(other.orderItemId));
    }

    @Override
    public int hashCode(){
        return getClass().hashCode();
    }

    @Override
    public String toString(){
        return "orderItemId : "+orderItemId;
    }
}
