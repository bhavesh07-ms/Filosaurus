package com.bhavesh.invoice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {
        "com.bhavesh.invoiceapi",        // For controllers
        "com.bhavesh.invoiceservice",    // For business services
        "com.bhavesh.invoicestorage",    // For S3/Mongo integrations
        "com.bhavesh.invoicecommon"      // For shared utils & DTOs
})
public class InvoiceApiApplication {
    public static void main(String[] args) {
        SpringApplication.run(InvoiceApiApplication.class, args);
    }
}
