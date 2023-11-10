package com.example.sdmisoback.repository;

import java.time.LocalDateTime;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import com.example.sdmisoback.view.AttachmentFileView;

public interface CustomAttachmentFileRepo {
    Page<AttachmentFileView> filterAttachments(
        PageRequest pr, String sortBy, boolean sortAsc,
        Integer fileId, String fileName, String fileDescription, LocalDateTime createdSince, String fileType,
        Integer projectId, String projectName, String projectType,
        Integer customerId, String customerName,
        Integer resourceId, String resourceName, String resourceType,
        Integer auctionId,
        Integer proposalId, String proposalLabel
    );
}
