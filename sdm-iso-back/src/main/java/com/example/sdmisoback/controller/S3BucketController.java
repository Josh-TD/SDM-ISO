package com.example.sdmisoback.controller;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.S3Object;
import org.apache.tomcat.util.http.fileupload.ByteArrayOutputStream;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/sdmisofiles")
@CrossOrigin(origins = "http://localhost:3000")
public class S3BucketController {

    @Autowired
    private AmazonS3 amazonS3;

    @Value("${aws.s3.bucketName}")
    private String S3_BUCKET_NAME;

    // Endpoint to get a list of all files in the S3 bucket
    // Testing: Copy and paste "http://localhost:8080/api/sdmisofiles/list"
    @GetMapping("/list")
    public ResponseEntity<List<String>> listFiles() {
        List<String> fileNames = amazonS3.listObjects(S3_BUCKET_NAME)
                .getObjectSummaries().stream()
                .map(s3ObjectSummary -> s3ObjectSummary.getKey())
                .collect(Collectors.toList());
        return ResponseEntity.ok(fileNames);
    }

    // Endpoint to view a single file
    // Testing: "http://localhost:8080/api/sdmisofiles/viewordownload?fileName=thingy.png"
    // Testing: The file displayed on the frontend after calling that endpoint should be thingy.png
    @GetMapping("/viewordownload")
    public ResponseEntity<byte[]> viewOrDownloadFile(@RequestParam(name = "fileName") String fileName) throws IOException {
        S3Object s3Object = amazonS3.getObject(S3_BUCKET_NAME, fileName);
        byte[] content = s3Object.getObjectContent().readAllBytes();

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
        headers.setContentDispositionFormData("attachment", fileName);

        return ResponseEntity.ok()
                .headers(headers)
                .body(content);
    }

    // Endpoint to download multiple files as a zip
    // Testing: "http://localhost:8080/api/sdmisofiles/download-zip?fileNames=FCM%20-%20Document%20Management%20App.pdf,Proposal_Attachement_Results_Query.xlsx,Final%20Arch%20Diagram.png"
    // Testing: The downloaded zip after calling that endpoint should contain these three files: FCM - Document Management App.pdf, Proposal_Attachement_Results_Query.xlsx, Final Arch Diagram.png
    @GetMapping("/download-zip")
    public ResponseEntity<byte[]> downloadMultipleFiles(@RequestParam(name = "fileNames") List<String> fileNames) throws IOException {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
        headers.setContentDispositionFormData("attachment", "files.zip");

        // Create a ByteArrayOutputStream to hold zip output
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ZipOutputStream zipOut = new ZipOutputStream(baos);

        // Iterate through file names, add each file to the zip output
        for (String fileName : fileNames) {
            S3Object s3Object = amazonS3.getObject(S3_BUCKET_NAME, fileName);
            byte[] content = s3Object.getObjectContent().readAllBytes();

            ZipEntry zipEntry = new ZipEntry(fileName);
            zipOut.putNextEntry(zipEntry);
            zipOut.write(content);
        }

        zipOut.close();
        baos.close();
        return ResponseEntity.ok()
                .headers(headers)
                .body(baos.toByteArray());
    }
}