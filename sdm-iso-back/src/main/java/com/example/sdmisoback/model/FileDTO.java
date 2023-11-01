package com.example.sdmisoback.model;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class FileDTO {
    private int fileId;
    private String fileName;
    private String fileDesc;
    private LocalDateTime fileCreateDate;

    private int custId;
    private String custName;

    private int projectId;
    private String projectName;
}
