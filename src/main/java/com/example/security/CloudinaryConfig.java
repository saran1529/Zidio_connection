package com.example.security;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Map;

@Configuration
public class CloudinaryConfig
{
    @Bean
    public Cloudinary cloudinary() {
        Map<String, String> config = ObjectUtils.asMap(
                "cloud_name" , "your_cloud_name",
                "api_key", "your_api_key",
                "api_secret", "your_api_secret");
        return  new Cloudinary(config);
    }


}
