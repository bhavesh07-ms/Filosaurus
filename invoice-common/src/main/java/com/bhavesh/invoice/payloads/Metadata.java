package com.bhavesh.invoice.payloads;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "invoice_metadata")
@Data
public class Metadata {
    @Id
    private String fileId;
    private String fileName;
    private String contentType;
    private long size;
    private String userId;
    private String checksum;


    // Getters/Setters
}