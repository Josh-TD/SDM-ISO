package com.example.sdmisoback.repository;

import java.time.LocalDateTime;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import com.example.sdmisoback.model.FiltersDTO;
import com.example.sdmisoback.view.AttachmentFileView;

public interface CustomAttachmentFileRepo {
    Page<AttachmentFileView> filterAttachments(FiltersDTO filters);
}
