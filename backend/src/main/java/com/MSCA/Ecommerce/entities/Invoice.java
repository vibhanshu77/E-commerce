package com.MSCA.Ecommerce.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import java.math.BigDecimal;
import java.time.LocalDateTime;

import com.MSCA.Ecommerce.enums.InvoiceStatus;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "invoice", indexes = {
        @Index(name = "idx_invoice_order", columnList = "order_id"),
        @Index(name = "idx_invoice_customer", columnList = "customer_id"),
        @Index(name = "idx_invoice_status", columnList = "status")
})
public class Invoice {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long invoiceNumber;

//    @Column(name = "invoice_number", unique = true, length = 50)
//    private String invoiceNumber;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id", nullable = false, unique = true)
    private CustomerOrder order;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "customer_id", nullable = false)
    private Customer customerId;

    // Use BigDecimal for precision in financial data
    @Column
    private Double amount;

    @Column
    private Double tax;

    @Column
    private Double discount;

    @Column
    private Double finalAmount;

    @Enumerated(EnumType.STRING)
    @Column(length = 20)
    private InvoiceStatus status;

    @Column(name = "pdf_url", length = 255)
    private String pdfUrl;

    @Column(name = "error_message", columnDefinition = "TEXT")
    private String errorMessage;

    @Column(name = "retry_count")
    private Integer retryCount = 0;

    @CreationTimestamp
    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    // To string, hashcode and equals //

    @Override
    public boolean equals(Object o){
        if(this == o) return true;

        if(!(o instanceof Invoice)) return false;

        Invoice other = (Invoice) o;
        return (invoiceNumber != null && invoiceNumber.equals(other.invoiceNumber));
    }

    @Override
    public int hashCode(){
        return getClass().hashCode();
    }

    public Invoice(CustomerOrder order, InvoiceStatus status){
        this.order = order;
        this.status = status;
    }

}
