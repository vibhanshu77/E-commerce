package com.MSCA.Ecommerce.repository;
import com.MSCA.Ecommerce.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepo extends JpaRepository<Product, Long> {

    @Query(value = "select * from product", nativeQuery = true)
    List<Product> getAllProducts();
}
