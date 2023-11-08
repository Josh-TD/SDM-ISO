package com.example.sdmisoback.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.sdmisoback.model.AttachmentFile;

@Repository
public interface AttachmentFileRepo extends JpaRepository<AttachmentFile, Integer>, CustomAttachmentFileRepo{
}
