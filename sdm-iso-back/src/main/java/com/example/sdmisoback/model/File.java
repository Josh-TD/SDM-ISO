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

    @Column(nullable = false)
    private Date createDate;

    @Column(length = 255)
    private String lastViewed;

    // constructor 
    public File(String description, String fileName, String filePath, Date createDate, String lastViewed) {
        this.description = description;
        this.fileName = fileName;
        this.filePath = filePath;
        this.createDate = createDate;
        this.lastViewed = lastViewed;
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

    private void setLastViewed(String lastViewed) {
        this.lastViewed = lastViewed;
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

    private String getLastViewed() {
        return lastViewed;
    }

    private String formatTimestamp(String timestamp) {
        //import date-time handler
        String displayTimestamp = "";
        return displayTimestamp;
    }




}
