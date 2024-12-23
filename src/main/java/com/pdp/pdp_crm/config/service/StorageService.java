package com.pdp.pdp_crm.config.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import software.amazon.awssdk.core.sync.ResponseTransformer;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.DeleteObjectRequest;
import software.amazon.awssdk.services.s3.model.GetObjectRequest;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;
import software.amazon.awssdk.services.s3.model.S3Exception;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.Objects;

@Service
@Slf4j
public class StorageService {

    @Value("${application.bucket.name}")
    private String bucketName;
    @Value("${cloud.aws.region.static}")
    private String region;

    private final S3Client s3Client;

    public StorageService(S3Client s3Client) {
        this.s3Client = s3Client;
    }


    public String uploadFile(MultipartFile file, String folder) {
        File fileObj = convertMultiPartFileToFile(file);
        String fileName = System.currentTimeMillis() + "_" + file.getOriginalFilename();
        String fullPath = folder + "/" + fileName;

        try {
            PutObjectRequest putObjectRequest = PutObjectRequest.builder()
                    .bucket(bucketName)
                    .key(fullPath)
                    .build();
            s3Client.putObject(putObjectRequest, Paths.get(fileObj.getPath()));
            log.info("File uploaded: {}", fullPath);

            return String.format("https://%s.s3.%s.amazonaws.com/%s", bucketName, region, fullPath);
        } catch (S3Exception e) {
            log.error("Error uploading file: {}", e.awsErrorDetails().errorMessage());
            return "File upload failed: " + e.awsErrorDetails().errorMessage();
        } finally {
            if (fileObj.exists()) {
                fileObj.delete();
            }
        }
    }

    public byte[] downloadFile(String fileName) {
        try {
            GetObjectRequest getObjectRequest = GetObjectRequest.builder()
                    .bucket(bucketName)
                    .key(fileName)
                    .build();
            return s3Client.getObject(getObjectRequest, ResponseTransformer.toBytes()).asByteArray();

        } catch (S3Exception e) {
            log.error("Error downloading file from S3: {}", e.awsErrorDetails().errorMessage());
            return null;
        }
    }


    public String deleteFile(String fileName) {
        try {
            DeleteObjectRequest deleteObjectRequest = DeleteObjectRequest.builder()
                    .bucket(bucketName)
                    .key(fileName)
                    .build();
            s3Client.deleteObject(deleteObjectRequest);

            log.info("File deleted: {}", fileName);
            return fileName + " removed.";
        } catch (S3Exception e) {
            log.error("Error deleting file from S3: {}", e.awsErrorDetails().errorMessage());
            return "File deletion failed: " + e.awsErrorDetails().errorMessage();
        }
    }

    private File convertMultiPartFileToFile(MultipartFile file) {
        File convertedFile = new File(Objects.requireNonNull(file.getOriginalFilename()));
        try (FileOutputStream fos = new FileOutputStream(convertedFile)) {
            fos.write(file.getBytes());
        } catch (IOException e) {
            log.error("Error converting MultipartFile to File: {}", e.getMessage());
        }
        return convertedFile;
    }
}
