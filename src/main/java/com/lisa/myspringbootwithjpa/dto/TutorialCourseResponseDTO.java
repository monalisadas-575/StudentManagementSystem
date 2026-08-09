package com.lisa.myspringbootwithjpa.dto;

import com.lisa.myspringbootwithjpa.model.enums.CourseCategory;
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
