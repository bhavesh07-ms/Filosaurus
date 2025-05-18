package com.bhavesh.invoice.service;


import com.bhavesh.invoice.payloads.Metadata;
import org.apache.tika.Tika;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;


public class MetadataExtractor {
    private final Tika tika = new Tika();

    public Metadata extract(MultipartFile file) {
        try {
            String type = tika.detect(file.getInputStream());
            Metadata metadata = new Metadata();
            metadata.setContentType(type);
            metadata.setFileName(file.getOriginalFilename());
            metadata.setSize(file.getSize());
            return metadata;
        } catch (Exception e) {
            throw new RuntimeException("Metadata extraction failed", e);
        }
    }
}
