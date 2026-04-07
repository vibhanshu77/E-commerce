package com.MSCA.Ecommerce.repository;
import com.MSCA.Ecommerce.entities.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeRepo extends JpaRepository<Employee, Long> {

    @Query("""
           Select e from Employee e WHERE e.email = :email
            """)
    Employee getUserDetailsByEmail(@Param("email") String emailId);

//    Employee findByEmail(String emailId);

    @Query("""
            Select e from Employee e WHERE e.username = :username
            """)
    Employee getUserDetailsByUsername(@Param("username") String userName);
}
