package com.MSCA.Ecommerce.responseMapperInterface;


import com.MSCA.Ecommerce.entities.Customer;
import com.MSCA.Ecommerce.responseDtos.CustomerResponseDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface CustomerMapper {

    //@Mapping(source = "shippingAdderss", target = "shippingAddress")
    //@Mapping(target = "customerOrders", ignore = true)
    CustomerResponseDto toDto(Customer customer);

    //Customer toEntity(CustomerResponseDto customerResponseDto);

}
