package com.MSCA.Ecommerce.controller;

import com.MSCA.Ecommerce.entities.Customer;
import com.MSCA.Ecommerce.entities.CustomerOrder;
import com.MSCA.Ecommerce.requestDto.CreateOrderRequestDto;
import com.MSCA.Ecommerce.responseDtos.CustomerOrderResponseDto;
import com.MSCA.Ecommerce.responseDtos.CustomerResponseDto;
import com.MSCA.Ecommerce.services.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/customer")
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @GetMapping("/data")
    public String data(Authentication authentication){

        return "data";
    }

    @GetMapping("/search")
    public List<Customer> getAllCustomers() {

        String search = "cust";
        boolean account_status = true;
        LocalDateTime created_from = LocalDateTime.of(2025,11,11,0,0);
        LocalDateTime created_to = LocalDateTime.of(2026,11,11,0,0);
        LocalDateTime last_login = LocalDateTime.of(2026,11,11,0,0);

        try {
            return  customerService.searchCustomers(search, account_status, created_from, created_to, last_login);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    @GetMapping("/{id}")
    public CustomerResponseDto getCustomer( @PathVariable Long id){

//        UserDetails userDetails = (UserDetails) authentication.getPrincipal();

        CustomerResponseDto customerResponseDto = customerService.customerRespDto(id);

        return ResponseEntity.ok(customerResponseDto).getBody();
    }

    @GetMapping("/order/{id}")
    public List<CustomerOrderResponseDto> get_customer_orders(@PathVariable Long id){

        List<CustomerOrderResponseDto> responseOrders = customerService.getAllOrders(id);

//        List<CustomerOrderResponseDto> customerOrderResponseDto = customerService.getCustomerOrders(id);

        return responseOrders;

//        return ResponseEntity.ok("Customer Orders : "+responseOrders);
//                ResponseEntity.ok("Customer Orders : "+responseOrders);
    }

    @PostMapping("/placeorder")
    public ResponseEntity placeOrderController(@RequestBody CreateOrderRequestDto createOrderRequestDto){

        String completionToken = customerService.placeOrderService(createOrderRequestDto);
//        System.out.println("Customer Order princial"+SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString());
//        System.out.println("other parameters: "+createOrderRequestDto.getCustomerId());
//        System.out.println("Order list: "+createOrderRequestDto.getOrderItemRequestDtoList());

        if(completionToken.equalsIgnoreCase("Order Saved successfully")){
            return ResponseEntity.status(HttpStatus.CREATED).body("Order Created Successfully");
        }
        else{
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Order can not be placed, Try again.");
        }
    }

}
