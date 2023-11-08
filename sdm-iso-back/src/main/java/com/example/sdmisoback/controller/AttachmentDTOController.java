package com.example.sdmisoback.controller;

import java.io.FileDescriptor;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


import com.example.sdmisoback.service.AttachmentService;
import com.example.sdmisoback.view.AttachmentFileView;


@RestController
@RequestMapping("/api/v3")
public class AttachmentDTOController {

    private AttachmentService service;

    public AttachmentDTOController(AttachmentService service){
        this.service = service;
    }

    // api endpoint at http://localhost:8080/api/v3/files/list?pageNum={int}&pageSize={int}&proposalId={int}
    // recommended pageNum=0&pageSize=3  proposalId optional but recommended = 333 
    // copy paste: http://localhost:8080/api/v3/files/list?pageNum=0&pageSize=3&proposalId=333
    @GetMapping("/files/list")
    public Page<AttachmentFileView> filterAttachments(
        // required parameters
        @RequestParam(name = "pageNum") int pageNum, 
        @RequestParam(name = "pageSize") int pageSize,
        @RequestParam(name = "sortBy") String sortBy,
        @RequestParam(name = "sortAsc") boolean sortAsc,

        // attachmentFile filters
        @RequestParam(name = "fileName", required = false) String fileName,
        @RequestParam(name = "fileId", required = false) Integer fileId,
        @RequestParam(name = "fileDescription", required = false) String fileDescription,

        // attachProposal filters
        // @RequestParam(name = "fileType", required = false) String fileType,

        // proposal filters
        @RequestParam(name = "proposalId", required = false) Integer proposalId,
        @RequestParam(name = "proposalLabel", required = false) String proposalLabel
        // @RequestParam(name = "proposalPeriodId", required = false) Integer proposalPeriodId

        ) { //end parameters
        PageRequest pr = PageRequest.of(pageNum, pageSize);
        return service.filterAttachments(
            pr, sortBy, sortAsc,
            fileName, fileId, fileDescription,
            proposalId, proposalLabel
        );
    }
}

