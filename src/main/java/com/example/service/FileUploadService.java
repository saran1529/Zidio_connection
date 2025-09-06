package com.example.service;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Map;

@Service
public class FileUploadService
{
    @Autowired
    private Cloudinary cloudinary;

    public String uploadFile(MultipartFile file) {

        try{
            Map<String,Object> uploadResult = cloudinary.uploader().upload(
                    file.getBytes(),
                    ObjectUtils.emptyMap());
            return uploadResult.get("secure_url").toString();
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("Failed to upload files",e);
        }
    }
}
