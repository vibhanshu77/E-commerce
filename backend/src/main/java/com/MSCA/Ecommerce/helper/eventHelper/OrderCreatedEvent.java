package com.MSCA.Ecommerce.helper.eventHelper;

import com.MSCA.Ecommerce.entities.Customer;
import com.MSCA.Ecommerce.entities.CustomerOrder;

public class OrderCreatedEvent {

    private CustomerOrder order;
    private Customer customer;

    public OrderCreatedEvent(CustomerOrder order, Customer customer){
        this.order = order;
        this.customer = customer;
    }

    public CustomerOrder getOrder(){
        return order;
    }
    public  Customer getCustomer(){
        return customer;
    }
}
