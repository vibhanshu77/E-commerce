package com.MSCA.Ecommerce.services;

import com.MSCA.Ecommerce.entities.Product;
import com.MSCA.Ecommerce.repository.ProductRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class ProductService {

    @Autowired
    ProductRepo productRepo;

    public List<Product> getProducts(){

        List<Product> products = productRepo.getAllProducts();

        if(products.size() == 0){
            throw new NoSuchElementException("No product present/Something went wrong");
        }

        return products;
    }

    public Product getAProduct(Long product_id){

        Product product = productRepo.findById(product_id).orElseThrow(()-> new NoSuchElementException("No Product found with product id :"+product_id));

        return product;
    }

}
