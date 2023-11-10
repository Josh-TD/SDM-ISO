package com.example.sdmisoback.controller;

import java.time.LocalDateTime;

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

    // set baseurl variable to http://localhost:8080/api
    // set filteringURL variable to http://localhost:8080/api/v3/files/list
    // then onto the end of that filtering URL: add on parameters
    // {{filteringURL}}?pageNum=0&pageSize=3&sortBy=attachmentId&sortAsc=true
    // ^ this is the url with only the required parameters

    // visit this link for details on the optional filters
    // https://imgur.com/a/JGFkbXR
    @GetMapping("/files/list")
    public Page<AttachmentFileView> filterAttachments(
        // required parameters
        @RequestParam(name = "pageNum") int pageNum, 
        @RequestParam(name = "pageSize") int pageSize,
        @RequestParam(name = "sortBy") String sortBy,
        @RequestParam(name = "sortAsc") boolean sortAsc,

        // attachmentFile filters
        @RequestParam(name = "fileId", required = false) Integer fileId,
        @RequestParam(name = "fileName", required = false) String fileName,
        @RequestParam(name = "fileDescription", required = false) String fileDescription,

        // TODO: figure out how to use this date?
        @RequestParam(name = "createdSince", required = false) LocalDateTime createdSince, 

        // attachProposal filters
        @RequestParam(name = "fileType", required = false) String fileType,

        // project filters
        @RequestParam(name = "projectId", required = false) Integer projectId,
        @RequestParam(name = "projectName", required = false) String projectName,
        @RequestParam(name = "projectType", required = false) String projectType,

        // customer filters
        @RequestParam(name = "customerId", required = false) Integer customerId,
        @RequestParam(name = "customerName", required = false) String customerName,

        // resource filters
        @RequestParam(name = "resourceId", required = false) Integer resourceId,
        @RequestParam(name = "resourceName", required = false) String resourceName,
        @RequestParam(name = "resourceType", required = false) String resourceType,

        // auction filter (??????)
        // TODO: figure out how to use dates
        @RequestParam(name = "auctionId", required = false) Integer auctionId,

        // proposal filters
        @RequestParam(name = "proposalId", required = false) Integer proposalId,
        @RequestParam(name = "proposalLabel", required = false) String proposalLabel

        // TODO: figure out how to use date again
        // @RequestParam(name = "proposalPeriodId", required = false) Integer proposalPeriodId

        ) { //end parameters
        PageRequest pr = PageRequest.of(pageNum, pageSize);

        // TODO: Get rid of this parameter bullshit and just make an object for it
        return service.filterAttachments(
            pr, sortBy, sortAsc,
            fileId, fileName, fileDescription, createdSince, fileType,
            projectId, projectName, projectType,
            customerId, customerName,
            resourceId, resourceName, resourceType,
            auctionId,
            proposalId, proposalLabel
        );
    }
}

