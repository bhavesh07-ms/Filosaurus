package com.bhavesh.invoice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication(scanBasePackages = "com.bhavesh.invoice")
@ComponentScan(basePackages = {
        "com.bhavesh.invoice.controller",        // For controllers
        "com.bhavesh.invoice.service",    // For business services
        "com.bhavesh.invoice.storage",    // For S3/Mongo integrations
        "com.bhavesh.invoice.common"// For shared utils & DTOs
})
public class InvoiceApiApplication {
    public static void main(String[] args) {
        SpringApplication.run(InvoiceApiApplication.class, args);
    }
}
