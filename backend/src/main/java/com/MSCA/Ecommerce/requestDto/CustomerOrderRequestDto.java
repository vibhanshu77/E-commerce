package com.MSCA.Ecommerce.requestDto;

import com.MSCA.Ecommerce.entities.Customer;

public record CustomerOrderRequestDto(

//    "customerEmail":"customer1@mail.com",
//            "qty": 2,
//            "productId":1

      String customerEmail,
      int qty,
      Long productId
//    List<OrderItemRequesrDto> orderItems

) {
}
