package com.bhavesh.invoice.payloads;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
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

    public void setId(String fileId) {
        this.fileId = fileId;
    }

    public void setOriginalFileName(String originalFilename) {
        this.fileName = originalFilename;
    }

    private boolean isPublic;

    // Add getter and setter
    public boolean isPublic() {
        return isPublic;
    }
    public void setPublic(boolean isPublic) {
        this.isPublic = isPublic;
    }

    // Getters/Setters
}