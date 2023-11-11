package com.example.sdmisoback.repository;

import org.springframework.data.domain.Page;

import com.example.sdmisoback.model.FiltersDTO;
import com.example.sdmisoback.view.AttachmentFileView;

public interface CustomAttachmentFileRepo {
    Page<AttachmentFileView> filterAttachments(FiltersDTO filters);
}
