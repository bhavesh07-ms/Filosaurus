package com.bhavesh.invoice.common.config;

import com.bhavesh.invoice.common.MetadataExtractor;
import com.bhavesh.invoice.common.S3ClientWrapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import software.amazon.awssdk.auth.credentials.AwsBasicCredentials;
import software.amazon.awssdk.auth.credentials.StaticCredentialsProvider;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.s3.S3Client;

import java.net.URI;

@Configuration
public class S3Config {

    @Bean
    public S3Client s3Client() {
        return S3Client.builder()
                .endpointOverride(URI.create("http://localhost:9000")) // If using MinIO locally
                .region(Region.US_EAST_1)
                .credentialsProvider(StaticCredentialsProvider.create(
                        AwsBasicCredentials.create("minio", "minio123") // Replace with actual credentials
                ))
                .build();
    }

    @Bean
    public S3ClientWrapper s3ClientWrapper(S3Client s3Client) {
        return new S3ClientWrapper(s3Client);
    }

    @Bean
    public MetadataExtractor metadataExtractor() {
        return new MetadataExtractor(); // assuming it has a default constructor
    }
}
