package com.example.sdmisoback.controller;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.S3Object;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/files")
public class AttachmentFileController {

    @Autowired
    private AmazonS3 amazonS3;

    @Value("${aws.s3.bucketName}")
    private String S3_BUCKET_NAME;

    @GetMapping("/list")
    public ResponseEntity<List<String>> listFiles() {
        List<String> fileNames = amazonS3.listObjects(S3_BUCKET_NAME)
                .getObjectSummaries().stream()
                .map(s3ObjectSummary -> s3ObjectSummary.getKey())
                .collect(Collectors.toList());
        return ResponseEntity.ok(fileNames);
    }

    @GetMapping("/download/{fileName}")
    public ResponseEntity<byte[]> downloadFile(@PathVariable String fileName) throws IOException {
        S3Object s3Object = amazonS3.getObject(S3_BUCKET_NAME, fileName);
        byte[] content = s3Object.getObjectContent().readAllBytes();

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
        headers.setContentDispositionFormData("attachment", fileName);

        return ResponseEntity.ok()
                .headers(headers)
                .body(content);
    }
}