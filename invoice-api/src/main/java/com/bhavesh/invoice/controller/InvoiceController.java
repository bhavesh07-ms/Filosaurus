package com.bhavesh.invoice.controller;

import com.bhavesh.invoice.service.InvoiceUploadService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.AccessDeniedException;

@RestController
@RequestMapping("/invoices")
public class InvoiceController {
    private final InvoiceUploadService uploadService;

    public InvoiceController(InvoiceUploadService uploadService) {
        this.uploadService = uploadService;
        System.out.println("InvoiceController initialized");
    }

    @PreAuthorize("hasRole('UPLOADER') or hasRole('ADMIN')")
    @PostMapping("/upload")
    public ResponseEntity<String> upload(@RequestParam("file") MultipartFile file,  @RequestParam("userId") String userId) {
        uploadService.upload(file, userId);
        return ResponseEntity.accepted().body("Upload initiated");
    }

    @PreAuthorize("hasRole('UPLOADER') or hasRole('VIEWER') or hasRole('ADMIN')")
    @GetMapping("/download/{fileId}")
    public ResponseEntity<?> downloadFile(@PathVariable String fileId, @RequestParam String userId) {
        try {
            byte[] data = uploadService.downloadFile(fileId, userId);
            return ResponseEntity.ok()
                    .header("Content-Disposition", "attachment; filename=\"" + fileId + "\"")
                    .body(data);
        } catch (AccessDeniedException e) {
            return ResponseEntity.status(403).body("Access Denied: " + e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body("Download failed");
        }
    }
    @GetMapping("/demo")
    public ResponseEntity<String> demoEndpoint() {
        return ResponseEntity.ok("Demo endpoint is working!");
    }
}