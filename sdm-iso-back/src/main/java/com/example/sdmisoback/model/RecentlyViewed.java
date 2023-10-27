package com.example.sdmisoback.model;
import java.time.LocalDateTime;

public class RecentlyViewed {
    private AttachmentFile file;
    private LocalDateTime timestamp;


    //constructors
    public RecentlyViewed(){
    }

    public RecentlyViewed(AttachmentFile file, LocalDateTime timestamp){
        this.file = file;
        this.timestamp = timestamp;
    }

    //getters and setters

    public AttachmentFile getFile(){
        return this.file;
    }

    public LocalDateTime getTimestamp(){
        return this.timestamp;
    }

    public void setFile(AttachmentFile file){
        this.file = file;
    }

    public void setTimestamp(LocalDateTime timestamp){
        this.timestamp = timestamp;
    }

}

// temporary, can override if needed for merge
