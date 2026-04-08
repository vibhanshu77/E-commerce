package com.MSCA.Ecommerce.helper.eventHelper;

import com.MSCA.Ecommerce.entities.Customer;
import com.MSCA.Ecommerce.entities.CustomerOrder;
import com.MSCA.Ecommerce.entities.Invoice;

public class OrderCreatedEvent {

    private CustomerOrder order;
    private Customer customer;
    private Invoice invoice;

    public OrderCreatedEvent(CustomerOrder order, Customer customer, Invoice invoice){
        this.order = order;
        this.customer = customer;
        this.invoice = invoice;
    }

    public CustomerOrder getOrder(){
        return order;
    }
    public  Customer getCustomer(){
        return customer;
    }
    public Invoice getInvoice(){return invoice;}

//    Long orderId;
//    Long customerId;
//    Long invoiceId;
//    public OrderCreatedEvent(Long orderId, Long customerId, Long invoiceId){
//
//        this.orderId = orderId;
//        this.customerId = customerId;
//        this.invoiceId = invoiceId;
//    }
//
//    public Long getOrderId(){
//        return orderId;
//    }
//
//    public Long getcustomerId(){
//        return customerId;
//    }
//
//    public Long getInvoiceId(){
//        return invoiceId;
//    }
}
