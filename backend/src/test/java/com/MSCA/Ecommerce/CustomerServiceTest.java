package com.MSCA.Ecommerce;

import com.MSCA.Ecommerce.entities.Customer;
//import com.MSCA.Ecommerce.repository.CustomerRepo;
import com.MSCA.Ecommerce.services.CustomerService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
//import org.springframework.test.context.bean.override.mockito.MockBean;
//import org.springframework.test.context.bean.override.mockito.MockBean;

import java.time.LocalDateTime;
import java.util.List;

@SpringBootTest(classes = {CustomerService.class})
public class CustomerServiceTest {

//    @Autowired
//    private CustomerRepo customerRepo;

   @Autowired
   private CustomerService customerService;

    @Test
    public void searchTest() {

        String searchInput = "cust";
        boolean isActive = true;

        LocalDateTime createdfrom  = LocalDateTime.of(2025,11,11,0,0,0,0);
        LocalDateTime createdto = LocalDateTime.of(2026,11,11,0,0,0,0);
        LocalDateTime lastLoginAfter = LocalDateTime.of(2025,11,11,0,0,0,0);

        List<Customer> resultList = customerService.searchCustomers(
                searchInput,
                isActive,
                createdfrom,
                createdto,
                lastLoginAfter
        );

        Assertions.assertNotNull(resultList);
       Assertions.assertFalse(resultList.isEmpty());
    }

}
