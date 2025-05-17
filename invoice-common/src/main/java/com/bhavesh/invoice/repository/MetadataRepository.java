package com.bhavesh.invoice.repository;


import com.bhavesh.invoice.payloads.Metadata;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface MetadataRepository extends MongoRepository<Metadata, String> {
}
