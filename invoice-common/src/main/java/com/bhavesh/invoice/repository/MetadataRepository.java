package com.bhavesh.invoice.repository;


import com.bhavesh.invoice.payloads.Metadata;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface MetadataRepository extends MongoRepository<Metadata, String> {
    Optional<Object> findByfileId(String fileId);
}
