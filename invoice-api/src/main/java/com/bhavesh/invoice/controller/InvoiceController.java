package com.bhavesh.invoice.controller;

import com.bhavesh.invoice.service.InvoiceUploadService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/invoices")
public class InvoiceController {
    private final InvoiceUploadService uploadService;

    public InvoiceController(InvoiceUploadService uploadService) {
        this.uploadService = uploadService;
        System.out.println("InvoiceController initialized");
    }

    @PostMapping("/upload")
    public ResponseEntity<String> upload(@RequestParam("file") MultipartFile file,  @RequestParam("userId") String userId) {
        uploadService.upload(file, userId);
        return ResponseEntity.accepted().body("Upload initiated");
    }

    @GetMapping("/demo")
    public ResponseEntity<String> demoEndpoint() {
        return ResponseEntity.ok("Demo endpoint is working!");
    }
}