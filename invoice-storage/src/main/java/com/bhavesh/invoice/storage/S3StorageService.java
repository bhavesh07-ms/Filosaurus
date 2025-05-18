package com.bhavesh.invoice.storage;

import com.bhavesh.invoice.common.MetadataExtractor;
import com.bhavesh.invoice.common.S3ClientWrapper;
import com.bhavesh.invoice.payloads.Metadata;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
public class S3StorageService {

    private final InvoiceMongoRepository metadataRepository;
    private final S3ClientWrapper s3ClientWrapper;
    private final MetadataExtractor metadataExtractor;

    public S3StorageService(InvoiceMongoRepository metadataRepository,
                            S3ClientWrapper s3ClientWrapper,
                            MetadataExtractor metadataExtractor) {
        this.metadataRepository = metadataRepository;
        this.s3ClientWrapper = s3ClientWrapper;
        this.metadataExtractor = metadataExtractor;
    }

//    public void uploadFile(MultipartFile file, String userId) throws IOException {
//        // Extract metadata from file (checksum, name, etc.)
//        Metadata metadata = metadataExtractor.extract(file);
//
//        // ✅ Deduplication Check
//        if (metadataRepository.existsByChecksum(metadata.getChecksum())) {
//            throw new RuntimeException("Duplicate file upload detected");
//        }
//
//        // Proceed with S3 upload
//        String fileId = UUID.randomUUID().toString();
//        s3ClientWrapper.upload(fileId, file);
//
//        // Save metadata after successful upload
//        metadata.setFileId(fileId);
//        metadata.setUserId(userId);
//        metadataRepository.save(metadata);
//    }
}
