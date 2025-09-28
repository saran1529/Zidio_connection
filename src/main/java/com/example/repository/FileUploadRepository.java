package com.example.repository;

import com.example.entity.FileUpload;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface FileUploadRepository extends JpaRepository<FileUpload, Long> {

    // Fetch all files uploaded by a specific user (student/admin)
    List<FileUpload> findByUploadedBy(String uploadedBy);

    // Optional: fetch a single file by publicId
    Optional<FileUpload> findByPublicId(String publicId);

    // Optional: check if a user has already uploaded a file with same name (avoid duplicates)
    boolean existsByUploadedByAndFileName(String uploadedBy, String fileName);
}
