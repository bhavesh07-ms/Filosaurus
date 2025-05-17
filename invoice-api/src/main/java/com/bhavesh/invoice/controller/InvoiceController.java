package com.bhavesh.invoice.controller;

import com.bhavesh.invoice.service.InvoiceUploadService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/invoices")
public class InvoiceController {
    private final InvoiceUploadService uploadService;

    public InvoiceController(InvoiceUploadService uploadService) {
        this.uploadService = uploadService;
    }

    @PostMapping("/upload")
    public ResponseEntity<String> upload(@RequestParam MultipartFile file, @RequestParam String userId) {
        uploadService.upload(file, userId);
        return ResponseEntity.accepted().body("Upload initiated");
    }
}