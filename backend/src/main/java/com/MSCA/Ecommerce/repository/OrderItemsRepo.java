package com.MSCA.Ecommerce.repository;
import com.MSCA.Ecommerce.entities.OrderItems;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderItemsRepo extends JpaRepository<OrderItems, Long> {
}
