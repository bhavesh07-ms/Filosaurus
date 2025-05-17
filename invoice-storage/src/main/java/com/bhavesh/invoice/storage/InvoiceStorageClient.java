package com.bhavesh.invoice.storage;


import org.springframework.web.multipart.MultipartFile;
import sun.jvm.hotspot.oops.Metadata;

public interface InvoiceStorageClient {
    void storeToS3(String fileId, MultipartFile file);
    void storeToMongo(String fileId, Metadata meta, String userId);
}
