package com.lisa.studentmanagementsystem.dto;

import com.lisa.studentmanagementsystem.model.enums.CourseCategory;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TutorialCourseResponseDTO {

    private String courseName;
    private CourseCategory category;
    private String tutorialWebsite;
    private String tutorName;
    private Boolean paidStatus;
}
