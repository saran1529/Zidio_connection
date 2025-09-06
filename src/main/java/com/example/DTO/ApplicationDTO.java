package com.example.DTO;

import com.example.enums.Status;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ApplicationDTO {
    private Long id;
    private Long studentId;
    private Long jobId;
    private String resumeURL;
    private Status status;
    private Date appliedDate;
}
