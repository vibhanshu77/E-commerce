package com.MSCA.Ecommerce.controller;

import com.MSCA.Ecommerce.entities.CustomerOrder;
import com.MSCA.Ecommerce.requestDto.CreateOrderRequestDto;
import com.MSCA.Ecommerce.requestDto.CustomerOrderRequestDto;
import com.MSCA.Ecommerce.security.CustomerDetailService;
import com.MSCA.Ecommerce.services.CustomerDetailServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/customer/order")
public class CustomerOrderController {

    private CustomerDetailServiceImpl.CustomerOrderService customerOrderService;

//    @Autowired
//    CustomerDetailServiceImpl.CustomerOrderService;

    public CustomerOrderController(CustomerDetailServiceImpl.CustomerOrderService customerOrderService){
        this.customerOrderService = customerOrderService;
    }

    // view all orders (as customer user calls this api)//
    @GetMapping("/allorders")
    public String getAllOrders(){

        return "Orders";
    }

    // view the specific order (as per the given order id)
//    @GetMapping("/{id}")
//    public String getCustomer(@PathVariable("id") Long orderId){
//
//        return ("order"+orderId).toString();
//    }

//    @PostMapping("/createorder")
//    public String createOrder(@RequestBody){
//
//    }

    @PostMapping("/createorder")
    public ResponseEntity<CustomerOrder> createOrder(@RequestBody CustomerOrderRequestDto customerOrderRequestDto){

        CustomerDetailService customerDetailService= (CustomerDetailService) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

//        customerDetailService.accountStatus();

        return ResponseEntity.status(HttpStatus.CREATED).body(customerOrderService.placeOrder( customerOrderRequestDto.productId(),
                customerOrderRequestDto.qty(), customerOrderRequestDto.customerEmail()));

//        if(customerDetailService.accountStatus().equals("ACTIVE")){
//
//            return ResponseEntity.status(HttpStatus.CREATED).body(customerOrderService.placeOrder( customerOrderRequestDto.productId(),
//                    customerOrderRequestDto.qty(), customerOrderRequestDto.customerEmail()));
//        }
//        else{
//            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
//        }
    }

}
