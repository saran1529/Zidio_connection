package com.example.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(
        name = "file_uploads",
        indexes = {
                @Index(name = "idx_uploaded_by", columnList = "uploadedBy")
        }
)
public class FileUpload {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 255)   // file name cannot be null
    private String fileName;

    @Column(nullable = false, length = 100)   // who uploaded it (student/admin email/username)
    private String uploadedBy;

    @Column(nullable = false)   // size in bytes
    private Long size;

    @Column(nullable = false, length = 500)   // file URL in Cloudinary
    private String url;

    @Column(nullable = false, unique = true, length = 255) // publicId must be unique
    private String publicId;

    @Column(nullable = false, updatable = false)
    private LocalDateTime uploadedAt;

    public FileUpload() {}

    public FileUpload(String fileName,
                      String uploadedBy,
                      Long size,
                      String url,
                      String publicId) {
        this.fileName = fileName;
        this.uploadedBy = uploadedBy;
        this.size = size;
        this.url = url;
        this.publicId = publicId;
        this.uploadedAt = LocalDateTime.now();
    }

    @PrePersist
    protected void onCreate() {
        if (uploadedAt == null) {
            uploadedAt = LocalDateTime.now();
        }
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getUploadedBy() {
        return uploadedBy;
    }

    public void setUploadedBy(String uploadedBy) {
        this.uploadedBy = uploadedBy;
    }

    public Long getSize() {
        return size;
    }

    public void setSize(Long size) {
        this.size = size;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getPublicId() {
        return publicId;
    }

    public void setPublicId(String publicId) {
        this.publicId = publicId;
    }

    public LocalDateTime getUploadedAt() {
        return uploadedAt;
    }

    public void setUploadedAt(LocalDateTime uploadedAt) {
        this.uploadedAt = uploadedAt;
    }
}
