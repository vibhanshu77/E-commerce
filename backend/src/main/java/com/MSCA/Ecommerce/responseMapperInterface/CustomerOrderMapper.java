package com.MSCA.Ecommerce.responseMapperInterface;

import com.MSCA.Ecommerce.entities.CustomerOrder;
import com.MSCA.Ecommerce.responseDtos.CustomerOrderResponseDto;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CustomerOrderMapper {

    List<CustomerOrderResponseDto> toDto(List<CustomerOrder> customerOrder);
}
