package com.MSCA.Ecommerce.responseDtos;

import com.MSCA.Ecommerce.entities.Product;

public record OrderItemDto(

        Long orderItemId,
        Product prduct,
        Integer quantity,
        Double unitPrice,
        Double discount,
        Double totalprice

) {
}
