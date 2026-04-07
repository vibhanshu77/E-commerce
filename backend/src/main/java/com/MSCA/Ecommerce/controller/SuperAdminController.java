package com.MSCA.Ecommerce.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/superadmin")
public class SuperAdminController {

    @GetMapping("/customer")
    public String getCustomerdetails(){

        return "Get token authentication";
    }

}
