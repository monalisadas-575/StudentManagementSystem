package com.lisa.studentmanagementsystem.model;

import com.lisa.studentmanagementsystem.model.enums.CourseCategory;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class TutorialCourse {
    @Id
    @GeneratedValue(strategy=GenerationType.SEQUENCE)
    private Long id;
    private String courseName;
    //No Course of same category will have same name

    @Enumerated(EnumType.STRING)
    private CourseCategory category;
    private String tutorialWebsite;
    private String tutorName;
    private Boolean paidStatus;

//    @ManyToMany(mappedBy = "tutorialCourses")
//    private List<Student> student;
}
