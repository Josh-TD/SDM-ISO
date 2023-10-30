package com.example.sdmisoback.model;
import java.time.LocalDateTime;
import java.util.Set;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class AttachmentFile {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="attachment_id")
    private int attachmentId;

    @Column(name="description", length = 255)
    private String description;
    
    @Column(name="file_name", length = 255)
    private String fileName;

    @Column(name="file_path", length = 255)
    private String filePath;

    @Column(name="create_date", nullable = false)
    private LocalDateTime createDate;

    @ManyToMany
    @JoinTable(
        name = "attach_proposal",
        joinColumns = @JoinColumn(name = "attachment_id"),
        inverseJoinColumns = @JoinColumn(name = "proposal_id"))
    private Set<ProposalInfo> proposals;
}