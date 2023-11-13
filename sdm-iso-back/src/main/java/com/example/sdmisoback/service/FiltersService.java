package com.example.sdmisoback.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import com.example.sdmisoback.dto.AttachmentFileView;
import com.example.sdmisoback.dto.FiltersDTO;
import com.example.sdmisoback.repository.FiltersRepo;


@Service
public class FiltersService {
    
    private final FiltersRepo filtersRepo;

    @Autowired
    public FiltersService(FiltersRepo filtersRepo){
        this.filtersRepo = filtersRepo;
    }

    public Page<AttachmentFileView> filterAttachments(FiltersDTO filters){
        return filtersRepo.filterAttachments(filters);
    }
}
