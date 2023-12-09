package com.example.sdmisoback.repository;

import org.springframework.data.domain.Page;

import com.example.sdmisoback.dto.FileViewDTO;
import com.example.sdmisoback.dto.FiltersDTO;

public interface CustomFiltersRepo {
    Page<FileViewDTO> filterAttachments(FiltersDTO filters);
}