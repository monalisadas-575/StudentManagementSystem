package com.lisa.myspringbootwithjpa.model;

import com.lisa.myspringbootwithjpa.model.enums.CourseCategory;
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
    //for eg. Two SpringBoot courses are there :
    //1> SpringBoot By Mr. Saumrit, Core Java By Saumrit
    //2> Chappar-Faad SpringBoot By Ms. DunDunLisa, Full-Paisa-Wassol Core Java By Ms DunDunLisa
    @Enumerated(EnumType.STRING)
    private CourseCategory category;
    private String tutorialWebsite;
    private String tutorName;
    private Boolean paidStatus;

//    @ManyToMany(mappedBy = "tutorialCourses")
//    private List<Student> student;
}
