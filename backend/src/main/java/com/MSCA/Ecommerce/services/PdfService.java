package com.MSCA.Ecommerce.services;

import com.MSCA.Ecommerce.entities.Invoice;
import com.openhtmltopdf.pdfboxout.PdfRendererBuilder;
//import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.thymeleaf.context.Context;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import org.thymeleaf.TemplateEngine;

import org.thymeleaf.spring6.SpringTemplateEngine;

import java.io.FileOutputStream;
import java.io.OutputStream;
import java.time.LocalDateTime;


@Service
public class PdfService {

    @Value("${app.upload.dir}")
    private String uploadDir;

    private final SpringTemplateEngine templateEngine;

    public PdfService(SpringTemplateEngine templateEngine) {
        this.templateEngine = templateEngine;
    }


    @Transactional(rollbackFor = Exception.class)
    public String generateInvoicePdf(Invoice invoice) {
        try {
            // STEP 1: Prepare data
            Context context = new Context();
            context.setVariable("invoiceNumber", invoice.getInvoiceNumber());
            context.setVariable("customerId", invoice.getCustomerId());
            context.setVariable("date", LocalDateTime.now());
            context.setVariable("amount", invoice.getAmount());
            context.setVariable("tax", invoice.getTax());
            context.setVariable("discount", invoice.getDiscount());
            context.setVariable("finalAmount", invoice.getFinalAmount());

            // STEP 2: Render HTML
            String htmlContent = templateEngine.process("invoice", context);
            htmlContent = htmlContent.trim(); // important!

            // STEP 3: Convert HTML → PDF
            String fileName = "invoice_" + invoice.getOrder().getOrderId() + ".pdf";
            String outputPath = uploadDir + File.separator + fileName;

            Files.createDirectories(Paths.get(uploadDir));

            try (OutputStream os = new FileOutputStream(outputPath)) {
                PdfRendererBuilder builder = new PdfRendererBuilder();
                builder.withHtmlContent(htmlContent, null);
                builder.toStream(os);
                builder.run();
            }

            System.out.println(outputPath);
            return outputPath;

            } catch (IOException e) {
                e.printStackTrace();
                System.err.println("Cannot find directory and path: "+ e.getMessage());
                return "FAILED";
            }catch (Exception e) {
                e.printStackTrace();
                System.err.println("PDF generation failed: " + e.getMessage());
                return "FAILED";
        }
    }
}
