package com.MSCA.Ecommerce.services;

import com.MSCA.Ecommerce.entities.Customer;
import com.MSCA.Ecommerce.entities.CustomerOrder;
import com.MSCA.Ecommerce.entities.OrderItems;
import com.MSCA.Ecommerce.entities.Product;
import com.MSCA.Ecommerce.enums.OrderStatus;
import com.MSCA.Ecommerce.repository.CustomerOrderRepo;
import com.MSCA.Ecommerce.repository.CustomerRepo;
import com.MSCA.Ecommerce.repository.OrderItemsRepo;
import com.MSCA.Ecommerce.security.CustomerDetailService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class CustomerDetailServiceImpl implements UserDetailsService {

    private CustomerRepo customerRepo;

    public CustomerDetailServiceImpl(CustomerRepo customerRepo){
        this.customerRepo = customerRepo;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {



        try{
            Customer customerdetails = customerRepo.findByCustomerEmail(email);

            if(customerdetails == null){
                throw new UsernameNotFoundException("Employee not found");
            }
            else{
                BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
                ((Customer) customerdetails).setCustomerPassword(passwordEncoder.encode(customerdetails.getCustomerPassword()));

//                return customerdetails;
                return new CustomerDetailService(customerdetails);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

//        if(customer != null){
//            return new CustomerDetailService(customer);
//        }
//        throw new UsernameNotFoundException("Customer in CustomerDetailServiceImpl not found");
    }

    @Service
    public static class CustomerOrderService {

        @Autowired
        ProductService productService;

        @Autowired
        CustomerRepo customerRepo;

        @Autowired
        CustomerOrderRepo customerOrderRepo;

        @Autowired
        OrderItemsService orderItemsService;

        @Autowired
        OrderItemsRepo orderItemsRepo;

        @Transactional
        public CustomerOrder placeOrder(Long product_id, int qty, String cEmail){

            Product product =  productService.getAProduct(product_id);

            Customer customer = customerRepo.findByCustomerEmail(cEmail);

            OrderItems orderItems = orderItemsService.createOrderItems(product, qty);

            Double totalAmount = orderItems.getTotalPrice();


            CustomerOrder customerOrder = CustomerOrder.builder()
                    .orderStatus(OrderStatus.CREATED)
                    .total_amount(totalAmount)
                                            .build();

            customerOrder.getOrderItems().add(orderItems);

    //        customerOrder.setOrderItems();
            customerOrder.setCustomer(customer);
            orderItems.setCustomerOrder(customerOrder);

            orderItemsRepo.save(orderItems);
            return customerOrderRepo.save(customerOrder);

        }

    }
}
