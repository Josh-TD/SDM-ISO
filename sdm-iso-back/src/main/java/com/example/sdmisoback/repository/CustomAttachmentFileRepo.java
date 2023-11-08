package com.example.sdmisoback.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import com.example.sdmisoback.view.AttachmentFileView;

public interface CustomAttachmentFileRepo {
    Page<AttachmentFileView> filterAttachments(PageRequest pr, Integer proposalId);
}
