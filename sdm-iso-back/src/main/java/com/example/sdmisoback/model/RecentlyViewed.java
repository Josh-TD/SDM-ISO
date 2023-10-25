package com.example.sdmisoback.model;
import java.util.Date;

public class RecentlyViewed {
    private AttachmentFile file;
    private Date timestamp;


    //constructors
    public RecentlyViewed(){
    }

    public RecentlyViewed(AttachmentFile file, Date timestamp){
        this.file = file;
        this.timestamp = timestamp;
    }

    //getters and setters

    public AttachmentFile getFile(){
        return this.file;
    }

    public Date getTimestamp(){
        return this.timestamp;
    }

    public void setFile(AttachmentFile file){
        this.file = file;
    }

    public void setTimestamp(Date timestamp){
        this.timestamp = timestamp;
    }

}

// temporary, can override if needed for merge
