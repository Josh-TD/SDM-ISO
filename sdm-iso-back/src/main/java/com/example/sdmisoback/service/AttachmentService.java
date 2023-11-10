package com.example.sdmisoback.service;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.example.sdmisoback.repository.AttachmentFileRepo;
import com.example.sdmisoback.view.AttachmentFileView;


@Service
public class AttachmentService {
    
    private final AttachmentFileRepo repo;

    public AttachmentService(@Qualifier("attachmentFileRepo") AttachmentFileRepo repo){
        this.repo = repo;
    }

    public Page<AttachmentFileView> filterAttachments(
        PageRequest pr, String sortBy, boolean sortAsc,
        Integer fileId, String fileName, String fileDescription, LocalDateTime createdSince, String fileType,
        Integer projectId, String projectName, String projectType,
        Integer customerId, String customerName,
        Integer resourceId, String resourceName, String resourceType,
        Integer auctionId,
        Integer proposalId, String proposalLabel
    ){
        return repo.filterAttachments(
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
