package com.MSCA.Ecommerce.repository;
import com.MSCA.Ecommerce.entities.CustomerOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CustomerOrderRepo extends JpaRepository<CustomerOrder, Long> {

    @Query("""
            Select o from Customer c 
            join c.customerOrders o 
            where c.customerId = :customerId""")
    public List<CustomerOrder> getAllCustomerOrders(@Param("customerId") Long cId);

}
