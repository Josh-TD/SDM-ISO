package com.example.sdmisoback.model;
import java.util.Date;
import jakarta.persistence.*;


public class File {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int fileId;

    @Column(length = 255)
    private String description;
    
    @Column(length = 255)
    private String fileName;

    @Column(length = 255)
    private String filePath;

    private Date createDate;

    // constructor 
    public File(String description, String fileName, String filePath, Date createDate) {
        this.description = description;
        this.fileName = fileName;
        this.filePath = filePath;
        this.createDate = createDate;
    }

    // Getters and setters for all attributes
    public int getAttachmentId() {
        return fileId;
    }

    public String getDescription() {
        return description;
    }

    // description is allowed to be set because its meant for internal use
    public void setDescription(String description) {
        this.description = description;
    }

    public String getFileName() {
        return fileName;
    }

    public String getFilePath() {
        return filePath;
    }

    public Date getCreateDate() {
        return createDate;
    }
}
