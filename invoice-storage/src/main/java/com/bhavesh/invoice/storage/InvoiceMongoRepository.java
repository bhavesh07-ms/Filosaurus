package com.bhavesh.invoice.storage;

import com.bhavesh.invoice.payloads.Metadata;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface InvoiceMongoRepository extends MongoRepository<Metadata, String> {
    boolean existsByChecksum(String checksum);
}

