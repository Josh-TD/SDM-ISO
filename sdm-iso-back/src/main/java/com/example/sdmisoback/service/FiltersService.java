package com.example.sdmisoback.service;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import com.example.sdmisoback.dto.AttachmentFileView;
import com.example.sdmisoback.dto.FiltersDTO;
import com.example.sdmisoback.repository.FiltersRepo;


@Service
public class FiltersService {
    
    private final FiltersRepo repo;

    public FiltersService(@Qualifier("attachmentFileRepo") FiltersRepo repo){
        this.repo = repo;
    }

    public Page<AttachmentFileView> filterAttachments(FiltersDTO filters){
        return repo.filterAttachments(filters);
    }
}
