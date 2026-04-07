package com.MSCA.Ecommerce.responseDtos;

import com.MSCA.Ecommerce.entities.CustomerOrder;

import java.util.Set;

public record CustomerResponseDto(

        Long customerId,
        String customerName,
        String contactNumber,
        String shippingAdderss,
        String billingAddress,
        Set<CustomerOrder> customerOrders

) {}


