package com.example.sdmisoback.controller;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;


import com.example.sdmisoback.service.AttachmentService;
import com.example.sdmisoback.view.AttachmentFileView;


@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "http://localhost:3000")
public class AttachmentDTOController {

    private AttachmentService service;

    public AttachmentDTOController(AttachmentService service){
        this.service = service;
    }

    // api endpoint at http://localhost:8080/api/files/list?pageNum={int}&pageSize={int}&proposalId={int}
    // recommended pageNum=0&pageSize=3  proposalId optional but recommended = 333 
    // copy paste: http://localhost:8080/api/files/list?pageNum=0&pageSize=3&proposalId=333
    @GetMapping("/files/list")
    @CrossOrigin(origins = "http://localhost:3000")
    public Page<AttachmentFileView> filterAttachments(
        @RequestParam(name = "pageNum") int page, 
        @RequestParam(name = "pageSize") int size, 
        @RequestParam(name = "proposalId", required = false) Integer id) {

        PageRequest pr = PageRequest.of(page, size);
        return service.filterAttachments(pr, id);
    }
}

