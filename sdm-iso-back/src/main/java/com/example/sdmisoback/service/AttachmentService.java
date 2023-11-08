package com.example.sdmisoback.service;

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

    public Page<AttachmentFileView> filterAttachments(PageRequest pr, Integer proposalId){
        return repo.filterAttachments(pr, proposalId);
    }
}
