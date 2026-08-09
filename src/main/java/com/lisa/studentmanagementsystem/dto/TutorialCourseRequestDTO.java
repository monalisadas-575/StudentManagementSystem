package com.lisa.studentmanagementsystem.dto;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TutorialCourseRequestDTO {

    @NotNull
    private String courseName;

    private String tutorialWebsite;
    private String tutorName;
    private Boolean paidStatus;
}
