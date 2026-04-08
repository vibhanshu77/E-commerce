package com.MSCA.Ecommerce.helper.eventHelper;

import com.MSCA.Ecommerce.entities.Customer;
import com.MSCA.Ecommerce.entities.CustomerOrder;

public class EmailGeneratingEvent {

    Customer customer;
    CustomerOrder order;
    String url;

    public EmailGeneratingEvent(Customer customer, CustomerOrder order, String url){
        this.customer = customer;
        this.url = url;
        this.order = order;
    }

    public Customer getCustomer(){
        return customer;
    }

    public String getUrl(){
        return url;
    }

    public CustomerOrder getOrder(){
        return order;
    }
}
