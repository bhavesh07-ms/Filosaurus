package com.bhavesh.invoice.storage;


import com.bhavesh.invoice.payloads.Metadata;
import org.springframework.web.multipart.MultipartFile;


public interface InvoiceStorageClient {
    void storeToS3(String fileId, MultipartFile file);

    //void uploadFile(String fileId, MultipartFile file);

    void storeToMongo(String fileId, Metadata meta, String userId);
}
