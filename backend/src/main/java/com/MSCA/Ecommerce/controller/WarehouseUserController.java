package com.MSCA.Ecommerce.controller;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/user")
public class WarehouseUserController {

    @GetMapping("/warehousesdmin/searchuser")
    public String warehouseUser(){

        return "Warehouse Admin is working fine";
    }

    @GetMapping("warehousestaff/data")
    public String warehousedata(){
        return "Warehouse staff is working fine";
    }
}
