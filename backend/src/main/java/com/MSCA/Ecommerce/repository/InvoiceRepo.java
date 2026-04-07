package com.MSCA.Ecommerce.repository;

import com.MSCA.Ecommerce.entities.Invoice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface InvoiceRepo extends JpaRepository<Invoice, Long> {

    @Query(value = """
       SELECT * FROM invoice 
       WHERE order_id = :order_id
       """, nativeQuery = true)
    Invoice findByOrderId(@Param("order_id") Long order_id);
//   Invoice findByOrderId(@Param("invoiceNumber") Long OrderId);
}
