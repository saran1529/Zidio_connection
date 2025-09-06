package com.example.entity;

import com.example.enums.JobType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@Table(name = "jobPosts")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class JobPost {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String jobTitle;
    private String jobDescription;
    private String jobLocation;
    private JobType jobType;
    private String companyName;
    private String postedByEmail;
    private Date postedDate;
}
