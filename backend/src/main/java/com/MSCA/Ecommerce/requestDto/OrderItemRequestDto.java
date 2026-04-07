package com.MSCA.Ecommerce.requestDto;

import com.MSCA.Ecommerce.entities.Product;

public record OrderItemRequestDto(

        Long ProductId,
        Integer quantity,
        Double unitPrice
//        Double discount,
//        Double totalprice

) {
}
