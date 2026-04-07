package com.MSCA.Ecommerce.controller;

import com.MSCA.Ecommerce.entities.Customer;
import com.MSCA.Ecommerce.repository.CustomerRepo;
import com.MSCA.Ecommerce.requestDto.RegisterCustomerDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.NoSuchElementException;

@RestController
@RequestMapping
public class RegisterCustomer {

    @Autowired
    CustomerRepo customerRepo;

    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;

    public String registerCustomer(@RequestBody RegisterCustomerDto registerCustomerDto){

        try{
            Customer customer = customerRepo.findByCustomerName(registerCustomerDto.getUserName());

            if(customer == null){
                throw new NoSuchElementException("No Such user found");
            }
            else{

                bCryptPasswordEncoder.encode(customer.getCustomerPassword());
            }

        }catch(Exception e){
            throw e;
        }

        return "will complete this method later!!";
    }
}
