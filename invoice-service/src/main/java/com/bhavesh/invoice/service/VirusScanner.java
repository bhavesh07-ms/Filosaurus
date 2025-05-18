package com.bhavesh.invoice.service;


import com.bhavesh.invoice.exception.VirusDetectedException;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

@Component
public class VirusScanner {
    public void ensureSafe(MultipartFile file) {
        // Simulated scan logic
        String filename = file.getOriginalFilename();
        if (filename != null && filename.endsWith(".exe")) {
            throw new VirusDetectedException("Potential virus detected: .exe files not allowed");
        }
        // ⬅️ In real-world: Integrate ClamAV via REST or socket here
    }
}

