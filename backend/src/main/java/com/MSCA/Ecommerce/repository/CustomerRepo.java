package com.MSCA.Ecommerce.repository;
import com.MSCA.Ecommerce.entities.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.query.JpqlQueryBuilder;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface CustomerRepo extends JpaRepository<Customer, Long> {

    @Query("""
    SELECT c
    FROM Customer c
    WHERE LOWER(c.customerEmail) LIKE LOWER(CONCAT('%', :customerEmail, '%'))
      AND c.isAccountActive = :isAccountActive""")
    List<Customer> findCustomers(
            @Param("customerEmail") String customerEmail,
            @Param("isAccountActive") boolean isAccountActive,
            @Param("createdFrom") LocalDateTime createdFrom,
            @Param("createdTo") LocalDateTime createdTo,
            @Param("lastLogin") LocalDateTime lastLogin
    );

    Customer findByCustomerEmail(String email);

//    Customer findByUserName(String username);

    Customer findByCustomerName(String userName);

    @Query("""
            Select c from Customer c where c.customerId = :customerId
            """)
    Customer findByCustomerId(@Param("customerId") Long customerId);
}
