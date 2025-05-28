package com.bhavesh.invoice.storage;



import com.bhavesh.invoice.repository.MetadataRepository;
import lombok.Value;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import  com.bhavesh.invoice.payloads.Metadata;
import software.amazon.awssdk.services.s3.S3Client;


@Component
public class InvoiceStorageClientImpl implements InvoiceStorageClient {


    @Autowired
    BucketProperties bucketProperties;

    private final S3Client s3Client;
    private final MetadataRepository metadataRepository;

    public InvoiceStorageClientImpl(S3Client s3Client, MetadataRepository metadataRepository)
    {

        this.s3Client = s3Client;
        this.metadataRepository = metadataRepository;
    }

    @Override
    public void storeToS3(String fileId, MultipartFile file) {
        try {
            s3Client.putObject(
                    builder -> builder.bucket(bucketProperties.getName()).key(fileId),
                    software.amazon.awssdk.core.sync.RequestBody.fromBytes(file.getBytes())
            );
        } catch (Exception e) {
            throw new RuntimeException("Failed to upload to S3", e);
        }
    }



    @Override
    public void storeToMongo(String fileId, Metadata meta, String userId) {
        meta.setFileId(fileId);
        meta.setUserId(userId);
        metadataRepository.save(meta);
    }


}
