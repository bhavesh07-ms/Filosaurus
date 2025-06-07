package com.bhavesh.invoice.storage;



import com.bhavesh.invoice.repository.MetadataRepository;
import io.minio.GetObjectArgs;
import io.minio.MinioClient;
import io.minio.PutObjectArgs;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import  com.bhavesh.invoice.payloads.Metadata;


import java.io.InputStream;


@Component
public class InvoiceStorageClientImpl implements InvoiceStorageClient {


    @Autowired
    BucketProperties bucketProperties;
    private final MinioClient minioClient;

    private final MetadataRepository metadataRepository;

    @Value("${minio.bucket}")
    private String bucketName;


    public InvoiceStorageClientImpl(MinioClient minioClient, MetadataRepository metadataRepository)
    {
        this.minioClient = minioClient;
        this.metadataRepository = metadataRepository;
    }





    @Override
    public void storeToMongo(String fileId, Metadata meta, String userId) {
        meta.setFileId(fileId);
        meta.setUserId(userId);
        metadataRepository.save(meta);
    }

    @Override
    public void storeTominiIO(String filePath, MultipartFile file) {

        try (InputStream inputStream = file.getInputStream()) {
            minioClient.putObject(
                    PutObjectArgs.builder()
                            .bucket(bucketName)
                            .object(filePath)
                            .stream(inputStream, file.getSize(), -1)
                            .contentType(file.getContentType())
                            .build()
            );
        } catch (Exception e) {
            throw new RuntimeException("Failed to upload to MinIO", e);
        }

    }

    @Override
    public byte[] downloadFile(String fileId) {
        try (InputStream stream = minioClient.getObject(
                GetObjectArgs.builder()
                        .bucket(bucketProperties.getName())
                        .object(fileId)
                        .build())) {

            return stream.readAllBytes();

        } catch (Exception e) {
            throw new RuntimeException("Error downloading file from MinIO", e);
        }
    }


    @Override
    public Metadata getMetadata(String fileId) {
        return (Metadata) metadataRepository.findByfileId(fileId)
                .orElseThrow(() -> new RuntimeException("Metadata not found"));
    }

}
