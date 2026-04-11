package com.MSCA.Ecommerce.services;
import com.MSCA.Ecommerce.entities.*;
import com.MSCA.Ecommerce.enums.InvoiceStatus;
import com.MSCA.Ecommerce.enums.OrderStatus;
import com.MSCA.Ecommerce.helper.eventHelper.OrderCreatedEvent;
import com.MSCA.Ecommerce.repository.*;
import com.MSCA.Ecommerce.requestDto.CreateOrderRequestDto;
import com.MSCA.Ecommerce.requestDto.OrderItemRequestDto;
import com.MSCA.Ecommerce.responseDtos.CustomerOrderResponseDto;
import com.MSCA.Ecommerce.responseDtos.CustomerResponseDto;
import com.MSCA.Ecommerce.responseMapperInterface.CustomerMapper;
import com.MSCA.Ecommerce.responseMapperInterface.CustomerOrderMapper;
//import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import static com.MSCA.Ecommerce.enums.InvoiceStatus.PENDING;

@Service
//@RequiredArgsConstructor
public class CustomerService {

    @Autowired
    private CustomerRepo customerRepo;

    @Autowired
    CustomerOrderRepo customerOrderRepo;

    @Autowired
    CustomerOrderMapper customerOrderMapper;

    @Autowired
    ProductRepo productRepo;

    @Autowired
    OrderItemsRepo orderItemsRepo;

    @Autowired
    InvoiceRepo invoiceRepo;

    @Autowired
    ApplicationEventPublisher eventPublisher;

    public List<Customer> searchCustomers(String search, boolean account_status,
                                LocalDateTime created_from, LocalDateTime created_to,
                                LocalDateTime last_login) {

        List<Customer> customers = customerRepo.findCustomers(search, account_status, created_from, created_to, last_login);
        for (Customer customer : customers) {
            System.out.println(customer);
        }
        System.out.println("List size: "+customers.size());
        return customers;
//        System.out.println(customers.size());

    }

    @Autowired
    private CustomerMapper customerMapper;

    public CustomerResponseDto customerRespDto(Long customerId){

        Customer customer = customerRepo.findById(customerId).orElseThrow(()->new RuntimeException("Customer Not found"));

        System.out.println(customer.getCustomerName());
        System.out.println(customer.getCustomerOrders());

        return customerMapper.toDto(customer);
    }

    public List<CustomerOrderResponseDto> getAllOrders(Long customer_id){

        // View order History //
        List<CustomerOrder> customer_order = customerOrderRepo.getAllCustomerOrders(customer_id);

        return customerOrderMapper.toDto(customer_order);

//        List<CustomerOrderResponseDto> customerOrderResponseDtos = customerOrderRepo.getAllCustomerOrders(customer_id);

//        return customer_order;
    }

    @Transactional
    public String placeOrderService(CreateOrderRequestDto createOrderRequestDto){

        CustomerOrder customerOrder = new CustomerOrder();
        // Fetiching customer//
        Customer customer = customerRepo.findByCustomerEmail(createOrderRequestDto.getCustomerEmail());
        // conflict line//
        customer.getCustomerOrders().add(customerOrder);
        // usual code //
        customerOrder.setCustomer(customer);
        customerOrder.setOrderStatus(OrderStatus.CREATED);

        customerOrder = customerOrderRepo.save(customerOrder);

        System.out.println("customer order type: "+customerOrder.getClass().getName());

        try{
//            CustomerOrder finalCustomerOrder = customerOrder;
//            CustomerOrder finalCustomerOrder = customerOrder;
//            createOrderRequestDto.getOrderItemRequestDtoList().forEach(l -> {
//                Optional<Product> product = Optional.ofNullable(productRepo.findById(l.ProductId()).orElseThrow(() -> new UsernameNotFoundException("No product present with this productId")));
//
//                OrderItems orderItems = OrderItems.builder()
//                        .quantity(l.quantity())
//                        .unitPrice(l.unitPrice())
//                        .product(product.get())
//                        .customerOrder(customerOrder)
//                        .build();
//                orderItemsRepo.save(orderItems);
//
//                customerOrder.getOrderItems().add(orderItems);
//                product.get().getOrderItems().add(orderItems);
//                productRepo.save(product.get());
//            });
            // New Approach //
            for(OrderItemRequestDto l : createOrderRequestDto.getOrderItemRequestDtoList()){
                Optional<Product> product = Optional.ofNullable(productRepo.findById(l.ProductId()).orElseThrow(() -> new UsernameNotFoundException("No product present with this productId")));
                OrderItems orderItems = OrderItems.builder()
                        .quantity(l.quantity())
                        .unitPrice(l.unitPrice())
                        .product(product.get())
                        .customerOrder(customerOrder)
                        .build();
                orderItemsRepo.save(orderItems);

                customerOrder.getOrderItems().add(orderItems);
                product.get().getOrderItems().add(orderItems);
                productRepo.save(product.get());
            }

            customerOrder.setCustomer(customer);

            customerOrder.setOrderStatus(OrderStatus.CREATED);
            customer.getCustomerOrders().add(customerOrder);

            customerOrderRepo.save(customerOrder);
            customerRepo.save(customer);

            // immidiately saving the invoice status //
            Invoice invoice = new Invoice();
            invoice.setCustomerId(customer);
            invoice.setOrder(customerOrder);
            invoice.setStatus(PENDING);
            invoiceRepo.save(invoice);

            // Publishing the event //
            eventPublisher.publishEvent(new OrderCreatedEvent(customerOrder, customer, invoice));
            return "Order Saved successfully";
//            eventPublisher.publishEvent(new OrderCreatedEvent(customerOrder.getOrderId(), customer.getCustomerId(), invoice.getInvoiceNumber()));
        }catch(Exception e){
            e.printStackTrace();
            throw new RuntimeException("Cannot Save order" + e.getMessage());
        }
    }

}
