package com.MSCA.Ecommerce.services;

import com.MSCA.Ecommerce.entities.Customer;
import com.MSCA.Ecommerce.entities.CustomerOrder;
import com.MSCA.Ecommerce.entities.Invoice;
import com.MSCA.Ecommerce.enums.InvoiceStatus;
import com.MSCA.Ecommerce.helper.eventHelper.EmailGeneratingEvent;
import com.MSCA.Ecommerce.helper.eventHelper.OrderCreatedEvent;
import com.MSCA.Ecommerce.repository.CustomerOrderRepo;
import com.MSCA.Ecommerce.repository.CustomerRepo;
import com.MSCA.Ecommerce.repository.InvoiceRepo;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.scheduling.annotation.Async;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.event.TransactionPhase;
import org.springframework.transaction.event.TransactionalEventListener;

import java.math.BigDecimal;
import java.util.Optional;

@Service
public class InvoiceService {

    @Autowired
    CustomerOrderRepo customerOrderRepo;

    @Autowired
    InvoiceRepo invoiceRepo;

    @Autowired
    CustomerRepo customerRepo;

    @Autowired
    PdfService pdfService;

    @Autowired
    ApplicationEventPublisher eventPublisher;

    @Async
    @Transactional
    @TransactionalEventListener(phase = TransactionPhase.AFTER_COMMIT)
    public void handleOrderInvoice(OrderCreatedEvent event){

        CustomerOrder order = event.getOrder();
        Customer customer = event.getCustomer();

        String url = generateInvoice(order, customer, event.getInvoice());

        eventPublisher.publishEvent(new EmailGeneratingEvent(customer, order, url));
    }

    public String generateInvoice(CustomerOrder order, Customer customer, Invoice invoice){

//        // fetching orders from customerorder table //
//        CustomerOrder customerOrder = customerOrderRepo.findById(order.getOrderId()).orElseThrow(()->new UsernameNotFoundException("No Order present"));
//        System.out.println("Customer order in Invoice"+customerOrder);
//
//        Customer getCustomer = customerRepo.findByCustomerId(customer.getCustomerId());
//        System.out.println("Customer in Invoice:"+customer);

        // Fetching Invoice from Invoice table //
//        Invoice invoice = invoiceRepo.findByOrderId(order.getOrderId());
        System.out.println("invoice id: "+invoice.getInvoiceNumber());
        System.out.println("invoice orderID: "+invoice.getOrder().getOrderId());

        // Set all the details in invoice object with Invoice Status "GENERATING" //
        invoice.setAmount(order.getTotal_amount());
        invoice.setTax(18.0);
        invoice.setStatus(InvoiceStatus.GENERATING);
        invoiceRepo.save(invoice); // need to save in old record.
        return pdfService.generateInvoicePdf(invoice);
    }

}
