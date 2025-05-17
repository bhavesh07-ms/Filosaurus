package com.bhavesh.invoice.payloads;


import java.io.Serializable;

public class InvoiceEvent implements Serializable {
    private String fileId;
    private String userId;

    public InvoiceEvent() {
    }

    public InvoiceEvent(String fileId, String userId) {
        this.fileId = fileId;
        this.userId = userId;
    }

    public String getFileId() {
        return fileId;
    }

    public void setFileId(String fileId) {
        this.fileId = fileId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    @Override
    public String toString() {
        return "InvoiceEvent{" +
                "fileId='" + fileId + '\'' +
                ", userId='" + userId + '\'' +
                '}';
    }
}
