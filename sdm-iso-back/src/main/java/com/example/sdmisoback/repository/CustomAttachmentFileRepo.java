package com.example.sdmisoback.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import com.example.sdmisoback.view.AttachmentFileView;

public interface CustomAttachmentFileRepo {
    Page<AttachmentFileView> filterAttachments(
        PageRequest pr, String sortBy, boolean sortAsc,
        String fileName, Integer fileId, String fileDescription,
        Integer proposalId, String proposalLabel
    );
}
