package com.example.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AnalyticalResponseDTO {
    private Long students;
    private Long recruiters;
    private Long jobPosts;
    private Long auth;
    private Long applications;
    private Long admins;
    private String fileUpload;
    private String email;
}
