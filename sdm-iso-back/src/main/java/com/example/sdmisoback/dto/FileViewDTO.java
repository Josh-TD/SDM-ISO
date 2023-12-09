package com.example.sdmisoback.dto;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FileViewDTO {
    public Integer attachmentId;
    public String fileName;
    public String fileDescription;
    public LocalDateTime fileCreateDate;
    public Integer projectId;
    public String projectName;
    public Integer customerId;
    public String customerName;
}