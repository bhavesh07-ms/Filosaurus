package com.bhavesh.invoice.service;

import com.bhavesh.invoice.payloads.InvoiceEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class KafkaInvoiceProducer {

    private static final Logger logger = LoggerFactory.getLogger(KafkaInvoiceProducer.class);
    private static final String TOPIC = "invoice-process";

    private final KafkaTemplate<String, InvoiceEvent> kafkaTemplate;

    public KafkaInvoiceProducer(KafkaTemplate<String, InvoiceEvent> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void sendInvoiceEvent(String fileId, String userId) {
        InvoiceEvent event = new InvoiceEvent(fileId, userId);

        kafkaTemplate.send("invoice-process", fileId, event)
                .whenComplete((result, ex) -> {
                    if (ex != null) {
                        System.err.println("Kafka send failed: " + ex.getMessage());
                    } else {
                        System.out.println("Kafka send success: " + result.getRecordMetadata());
                    }
                });
    }

}
