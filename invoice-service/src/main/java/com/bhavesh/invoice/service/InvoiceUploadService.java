package com.bhavesh.invoice.service;




import com.bhavesh.invoice.common.MetadataExtractor;
import com.bhavesh.invoice.exception.FileUploadException;
import com.bhavesh.invoice.payloads.Metadata;
import com.bhavesh.invoice.storage.InvoiceStorageClient;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import java.nio.file.AccessDeniedException;
import java.util.UUID;

@Service
public class InvoiceUploadService {

    private final VirusScanner scanner;
    private final KafkaInvoiceProducer kafka;
    private final MetadataExtractor extractor;
    private final InvoiceStorageClient storage;

    public InvoiceUploadService(VirusScanner scanner,
                                KafkaInvoiceProducer kafka,
                                MetadataExtractor extractor,
                                InvoiceStorageClient storage) {
        this.scanner = scanner;
        this.kafka = kafka;
        this.extractor = extractor;
        this.storage = storage;
    }

    public void upload(MultipartFile file, String userId) {
        try {
            scanner.ensureSafe(file);

            String fileId = UUID.randomUUID().toString();
            String checksum = DigestUtils.sha256Hex(file.getInputStream());

            Metadata meta = MetadataExtractor.extractMetadata(file,fileId,userId);
            meta.setChecksum(checksum);
            meta.setFileId(fileId);
            meta.setUserId(userId);
            String filePath = userId + "/" + fileId;
            storage.storeTominiIO(filePath, file);
            storage.storeToMongo(fileId, meta, userId);

            //kafka.sendInvoiceEvent(fileId, userId);

        } catch (IOException e) {
            throw new FileUploadException("Error processing invoice upload", e);
        }
    }
    public byte[] downloadFile(String fileId, String userId) throws IOException {
        Metadata meta = storage.getMetadata(fileId);

        if (!meta.isPublic() && !meta.getUserId().equals(userId)) {
            throw new AccessDeniedException("User not authorized to access this file");
        }

        return storage.downloadFile(fileId);
    }

}
