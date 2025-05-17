package com.bhavesh.invoice.dto;


import lombok.Data;

@Data
public class InvoiceEvent {
    private String fileId;
    private String userId;
    private String status;
}
