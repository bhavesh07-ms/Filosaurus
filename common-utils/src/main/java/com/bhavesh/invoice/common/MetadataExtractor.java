package com.bhavesh.invoice.common;


import com.bhavesh.invoice.payloads.Metadata;
import org.apache.tika.Tika;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class MetadataExtractor {

    private static final Tika tika = new Tika();

    public static Metadata extractMetadata(MultipartFile file, String fileId, String userId) throws IOException {
        Metadata metadata = new Metadata();
        metadata.setId(fileId);
        metadata.setUserId(userId);
        metadata.setOriginalFileName(file.getOriginalFilename());
        metadata.setSize(file.getSize());

        // Prefer Tika for content type detection if possible
        try {
            metadata.setContentType(tika.detect(file.getInputStream()));
        } catch (Exception e) {
            metadata.setContentType(file.getContentType()); // fallback
        }

        metadata.setChecksum(generateChecksum(file.getBytes()));
        return metadata;
    }

    private static String generateChecksum(byte[] content) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            byte[] hash = md.digest(content);
            StringBuilder sb = new StringBuilder();
            for (byte b : hash) {
                sb.append(String.format("%02x", b));
            }
            return sb.toString();
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("Failed to generate checksum", e);
        }
    }

}
