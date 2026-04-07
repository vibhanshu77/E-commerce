package com.MSCA.Ecommerce.services;

import com.MSCA.Ecommerce.entities.Customer;
import com.MSCA.Ecommerce.entities.CustomerOrder;
import com.MSCA.Ecommerce.entities.Invoice;
import com.MSCA.Ecommerce.helper.eventHelper.OrderCreatedEvent;
import com.MSCA.Ecommerce.repository.CustomerOrderRepo;
import com.MSCA.Ecommerce.repository.CustomerRepo;
import com.MSCA.Ecommerce.repository.InvoiceRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.event.TransactionPhase;
import org.springframework.transaction.event.TransactionalEventListener;

import java.util.Optional;

@Service
public class InvoiceService {

    @Autowired
    CustomerOrderRepo customerOrderRepo;

    @Autowired
    InvoiceRepo invoiceRepo;

    @Autowired
    CustomerRepo customerRepo;

    @Async
    @TransactionalEventListener(phase = TransactionPhase.AFTER_COMMIT)
    public void handleOrderInvoice(OrderCreatedEvent event){

        CustomerOrder order = event.getOrder();
        Customer customer = event.getCustomer();

        generateInvoice(order, customer);
    }

    public void generateInvoice(CustomerOrder order, Customer customer){

        // fetching orders from customerorder table //
        CustomerOrder customerOrder = customerOrderRepo.findById(order.getOrderId()).orElseThrow(()->new UsernameNotFoundException("No Order present"));
        System.out.println("Customer order in Invoice"+customerOrder);

        Customer getCustomer = customerRepo.findByCustomerId(customer.getCustomerId());
        System.out.println("Customer in Invoice:"+customer);

        // Fetching Invoice from Invoice table //
        Invoice invoice = invoiceRepo.findByOrderId(order.getOrderId());
        System.out.println("invoice in generateInvoice"+invoice);

        // Set all the details in invoice object with Invoice Status "GENERATING" //
        /* Code here

        */

    }

}
