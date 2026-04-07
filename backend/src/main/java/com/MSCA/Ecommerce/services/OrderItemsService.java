package com.MSCA.Ecommerce.services;

import com.MSCA.Ecommerce.entities.OrderItems;
import com.MSCA.Ecommerce.entities.Product;
import com.MSCA.Ecommerce.repository.OrderItemsRepo;
import org.springframework.stereotype.Service;

@Service
public class OrderItemsService {

    private OrderItemsRepo orderItemsRepo;
    private ProductService productService;

    public OrderItemsService(OrderItemsRepo orderItemsRepo){
        this.orderItemsRepo = orderItemsRepo;
    }

    public OrderItems createOrderItems(Product product, int qty){

        Double discount = product.getProductPrice()*(2/10);

        Double totalPricePerOrderItem = product.getProductPrice()*qty+10;

        OrderItems orderItems = OrderItems.builder()
                                .quantity(qty)
                                .unitPrice(product.getProductPrice())
                                .discount(discount)
                                .totalPrice(totalPricePerOrderItem)
                                .build();
        orderItems.setProduct(product);

        return orderItems;
//        orderItems

        //return orderItemsRepo.save(orderItems);
    }

}
