package com.MSCA.Ecommerce.controller;

import com.MSCA.Ecommerce.entities.Product;
import com.MSCA.Ecommerce.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
public class ProductController {

    @Autowired
    ProductService productService;

    @GetMapping("/customer/products")
    public List<Product> getAllProducts(){

        List<Product> allProducts = productService.getProducts();

        return allProducts;
    }

    @PostMapping("/product/createproduct")
    public String createProduct(){
        return "create product";
    }

}
