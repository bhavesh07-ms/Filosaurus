package com.bhavesh.invoice.storage;


import jdk.jfr.internal.MetadataRepository;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;
import software.amazon.awssdk.services.s3.S3Client;
import  com.bhavesh.invoice.payloads.Metadata;

@Component
public class InvoiceStorageClientImpl implements InvoiceStorageClient {

    private final S3Client s3Client;
    private final MetadataRepository metadataRepository;

    public InvoiceStorageClientImpl(S3Client s3Client, MetadataRepository metadataRepository) {
        this.s3Client = s3Client;
        this.metadataRepository = metadataRepository;
    }

    @Override
    public void storeToS3(String fileId, MultipartFile file) {
        try {
            s3Client.putObject(builder -> builder.bucket("your-bucket").key(fileId),
                    software.amazon.awssdk.core.sync.RequestBody.fromBytes(file.getBytes()));
        } catch (Exception e) {
            throw new RuntimeException("Failed to store to S3", e);
        }
    }



    @Override
    public void storeToMongo(String fileId, Metadata meta, String userId) {
        meta.setFileId(fileId);
        meta.setUserId(userId);
        metadataRepository.save(meta);
    }


}
