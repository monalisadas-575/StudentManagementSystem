package com.lisa.myspringbootwithjpa.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TutorialCourseRequestDTO {

    private String courseName;
    private String tutorialWebsite;
    private String tutorName;
    private Boolean paidStatus;
}
