package com.example.sdmisoback.controller;

import com.example.sdmisoback.model.AttachmentFile;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

@RestController
public class AttachmentFileController {
    @CrossOrigin(origins = "http://localhost:3000")
    @GetMapping("/files")
    public List<AttachmentFile> getFiles() {
        Date testDate = new Date();
        AttachmentFile fileOne = new AttachmentFile("nice file 1", "File One", "s3://p1/fileone.png", testDate);
        AttachmentFile fileTwo = new AttachmentFile("nice file 2", "File Two", "s3://p1/filetwo.pdf", testDate);
        AttachmentFile fileThree = new AttachmentFile("nice file 3", "File Three", "s3://p1/filethree.msg", testDate);
        return Arrays.asList(fileOne, fileTwo, fileThree);
    }
}

// temporary, can override if needed for merge