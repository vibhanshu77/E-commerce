package com.MSCA.Ecommerce.responseDtos;

import com.MSCA.Ecommerce.entities.OrderItems;

import java.util.List;

public record CustomerOrderResponseDto(

        Long orderId,
        String orderStatus,
        Double total_amount,
        List<OrderItems>orderItems
) {
}
