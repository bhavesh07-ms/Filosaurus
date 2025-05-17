package com.bhavesh.invoice.dto;

import lombok.Data;

import java.time.Instant;

@Data
public class Metadata {
    private String fileName;
    private long size;
    private String mimeType;
    private Instant uploadTime;
    private String checksum;
    // constructor, getters, setters
}
