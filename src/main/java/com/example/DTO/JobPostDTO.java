package com.example.DTO;

import com.example.enums.JobType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class JobPostDTO {
    private Long id;
    private String jobTitle;
    private JobType jobType;
    private String jobLocation;
    private String jobDescription;
    private String companyName;
    private String postedByEmail;
    private Date postedDate;
}
