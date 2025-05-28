package com.bhavesh.invoice.common;




import org.springframework.stereotype.Component;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;
import software.amazon.awssdk.services.s3.model.S3Exception;

import java.io.InputStream;
import java.nio.file.Paths;

@Component
public class S3ClientWrapper {

    private final S3Client s3Client;

    public S3ClientWrapper(S3Client s3Client) {
        this.s3Client = s3Client;
    }

    public void uploadFile(String bucketName, String key, InputStream inputStream, long contentLength) {
        try {
            PutObjectRequest putObjectRequest = PutObjectRequest.builder()
                    .bucket(bucketName)
                    .key(key)
                    .build();

            s3Client.putObject(putObjectRequest,
                    software.amazon.awssdk.core.sync.RequestBody.fromInputStream(inputStream, contentLength));
        } catch (S3Exception e) {
            throw new RuntimeException("Failed to upload to S3", e);
        }
    }
}

