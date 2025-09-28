package com.example.service;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import com.example.entity.FileUpload;
import com.example.repository.FileUploadRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class FileUploadService {

    private final Cloudinary cloudinary;
    private final FileUploadRepository fileUploadRepository;

    public FileUploadService(Cloudinary cloudinary, FileUploadRepository fileUploadRepository) {
        this.cloudinary = cloudinary;
        this.fileUploadRepository = fileUploadRepository;
    }

    // ========================
    // Upload File
    // ========================
    public String uploadFile(MultipartFile file, String uploadedBy) throws IOException {
        // Validate file type
        String fileName = file.getOriginalFilename();
        if (fileName == null ||
                !(fileName.endsWith(".pdf") || fileName.endsWith(".doc") || fileName.endsWith(".docx"))) {
            throw new IllegalArgumentException("Only PDF, DOC, or DOCX files are allowed.");
        }

        // Validate file size (5 MB = 5 * 1024 * 1024 bytes)
        long maxSize = 5 * 1024 * 1024;
        if (file.getSize() > maxSize) {
            throw new IllegalArgumentException("File size must not exceed 5 MB.");
        }

        // Check for duplicate resume upload (same user + same file name)
        if (fileUploadRepository.existsByUploadedByAndFileName(uploadedBy, fileName)) {
            throw new IllegalArgumentException("You have already uploaded a resume with this name.");
        }

        // Upload to Cloudinary
        Map uploadResult = cloudinary.uploader().upload(file.getBytes(),
                ObjectUtils.asMap("resource_type", "auto"));

        String url = uploadResult.get("secure_url").toString();
        String publicId = uploadResult.get("public_id").toString();

        // Save metadata in database
        FileUpload fileUpload = new FileUpload(fileName, uploadedBy, file.getSize(), url, publicId);

        fileUploadRepository.save(fileUpload);

        return url;
    }

    // ========================
    // Get All Files (Admin)
    // ========================
    public List<FileUpload> getAllFiles() {
        return fileUploadRepository.findAll();
    }

    // ========================
    // Get Files by User Email
    // ========================
    public List<FileUpload> getFilesByEmail(String email) {
        return fileUploadRepository.findByUploadedBy(email);
    }

    // ========================
    // Get File by ID
    // ========================
    public Optional<FileUpload> getFileById(Long id) {
        return fileUploadRepository.findById(id);
    }

    // ========================
    // Delete File by ID
    // ========================
    public boolean deleteFile(Long id) throws IOException {
        Optional<FileUpload> fileOpt = fileUploadRepository.findById(id);
        if (fileOpt.isPresent()) {
            FileUpload file = fileOpt.get();

            // Delete from Cloudinary
            cloudinary.uploader().destroy(file.getPublicId(), ObjectUtils.emptyMap());

            // Delete from DB
            fileUploadRepository.deleteById(id);
            return true;
        }
        return false;
    }

    // ========================
    // Delete File by publicId
    // ========================
    public boolean deleteFileByPublicId(String publicId) throws IOException {
        Optional<FileUpload> fileOpt = fileUploadRepository.findByPublicId(publicId);
        if (fileOpt.isPresent()) {
            FileUpload file = fileOpt.get();

            // Delete from Cloudinary
            cloudinary.uploader().destroy(publicId, ObjectUtils.emptyMap());

            // Delete from DB
            fileUploadRepository.delete(file);
            return true;
        }
        return false;
    }
}
