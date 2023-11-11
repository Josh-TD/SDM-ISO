package com.example.sdmisoback.service;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.example.sdmisoback.model.FiltersDTO;
import com.example.sdmisoback.repository.AttachmentFileRepo;
import com.example.sdmisoback.view.AttachmentFileView;


@Service
public class AttachmentService {
    
    private final AttachmentFileRepo repo;

    public AttachmentService(@Qualifier("attachmentFileRepo") AttachmentFileRepo repo){
        this.repo = repo;
    }

    public Page<AttachmentFileView> filterAttachments(FiltersDTO filters){
        return repo.filterAttachments(filters);
    }
}
