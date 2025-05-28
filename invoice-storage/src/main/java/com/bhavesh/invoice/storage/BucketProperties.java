package com.bhavesh.invoice.storage;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "application.bucket")
@Data
public class BucketProperties {
    private String name;

}
