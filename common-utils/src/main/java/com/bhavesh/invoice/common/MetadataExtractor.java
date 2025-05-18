package com.bhavesh.invoice.common;


import com.bhavesh.invoice.payloads.Metadata;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class MetadataExtractor {

    public static Metadata extractMetadata(MultipartFile file, String fileId, String userId) throws IOException {
        Metadata metadata = new Metadata();
        metadata.setId(fileId);
        metadata.setOriginalFileName(file.getOriginalFilename());
        metadata.setSize(file.getSize());
        metadata.setContentType(file.getContentType());
        metadata.setUserId(userId);
        metadata.setChecksum(generateChecksum(file.getBytes()));
        return metadata;
    }

    private static String generateChecksum(byte[] content) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            byte[] hash = md.digest(content);
            StringBuilder sb = new StringBuilder();
            for (byte b : hash)
                sb.append(String.format("%02x", b));
            return sb.toString();
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("Failed to generate checksum", e);
        }
    }
}
