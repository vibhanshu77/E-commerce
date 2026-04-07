package com.MSCA.Ecommerce.requestDto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public class CreateOrderRequestDto {

    private String customerEmail;
    private Long customerId; // Do we need to keep the specific customer Id or Auto generated will be better?
    List<OrderItemRequestDto> orderItemRequestDtoList;
}
