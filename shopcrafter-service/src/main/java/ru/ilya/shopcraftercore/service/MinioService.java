package ru.ilya.shopcraftercore.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;
import software.amazon.awssdk.services.s3.model.S3Exception;

import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.UUID;

@Service
public class MinioService {
    private static final Logger log = LoggerFactory.getLogger(MinioService.class);
    private final S3Client s3Client;
    private final String bucketName;
    private final String host;

    public MinioService(S3Client s3Client,
                        @Value("${aws.s3.bucket}") String bucketName,
                        @Value("${aws.s3.host}") String host) {
        this.s3Client = s3Client;
        this.bucketName = bucketName;
        this.host = host;
    }

    public String uploadFile(MultipartFile file) throws IOException {
        String key = UUID.randomUUID() + "_" + URLEncoder.encode(file.getOriginalFilename(), StandardCharsets.UTF_8);
        try {
            s3Client.putObject(PutObjectRequest.builder()
                            .bucket(bucketName)
                            .key(key)
                            .contentType(file.getContentType())
                            .build(),
                    software.amazon.awssdk.core.sync.RequestBody.fromInputStream(file.getInputStream(), file.getSize()));
        } catch (S3Exception e) {
            log.error("Failed to upload file", e);
            throw new IOException("Failed to upload file to MinIO", e);
        }
        return host.replaceAll("/$","") + "/" + bucketName + "/" + key;
    }
} 