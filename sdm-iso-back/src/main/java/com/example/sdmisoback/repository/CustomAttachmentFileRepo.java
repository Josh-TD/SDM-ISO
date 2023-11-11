package com.example.sdmisoback.repository;

import org.springframework.data.domain.Page;

import com.example.sdmisoback.dto.AttachmentFileView;
import com.example.sdmisoback.dto.FiltersDTO;

public interface CustomAttachmentFileRepo {
    Page<AttachmentFileView> filterAttachments(FiltersDTO filters);
}
