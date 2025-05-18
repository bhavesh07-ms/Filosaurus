package com.bhavesh.invoice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
//@ComponentScan(basePackages = {
//        "com.example.invoiceapi",        // For controllers
//        "com.example.invoiceservice",    // For business services
//        "com.example.invoicestorage",    // For S3/Mongo integrations
//        "com.example.invoicecommon"      // For shared utils & DTOs
//})
public class InvoiceApiApplication {
    public static void main(String[] args) {
        SpringApplication.run(InvoiceApiApplication.class, args);
    }
}
