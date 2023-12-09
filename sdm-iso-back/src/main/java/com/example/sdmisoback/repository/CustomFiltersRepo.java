package com.example.sdmisoback.repository;

import java.util.List;

import org.springframework.data.domain.Page;

import com.example.sdmisoback.dto.FileViewDTO;
import com.example.sdmisoback.dto.FiltersDTO;

public interface CustomFiltersRepo {
    List<FileViewDTO> getRecentlyViewed(List<Integer> fileIds);
    Page<FileViewDTO> filterAttachments(FiltersDTO filters);
}
