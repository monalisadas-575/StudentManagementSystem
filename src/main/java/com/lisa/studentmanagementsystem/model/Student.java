package com.lisa.studentmanagementsystem.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import jakarta.persistence.Id;

import java.util.List;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "Student")//if i give any other name , that i have to use instead of Student, under @Query
@Access(AccessType.FIELD)
@NamedQueries({
        @NamedQuery(name="Student.findNRIStudentsFromGivenState",
                query = "select s from Student s where s.bioData.nriStatus= true") // s.address.state= ?1 AND
})
@Cacheable(value = true)
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private long id;

    private String rollId;
    private String name;
    private String standard;
    private Integer age;
    @OneToOne(orphanRemoval = true,cascade = {CascadeType.ALL})
    @JoinColumn(name = "address_id",referencedColumnName = "id")
    @JsonBackReference
    private Address address;

//    @OneToOne
//    @JoinColumns({
//            @JoinColumn(name = "assignments_student_id",referencedColumnName = "studentId"),
//            @JoinColumn(name = "assignments_subject_id",referencedColumnName ="subjectId" )
//    })
//    private Assignment assignment;

    @Embedded
    private BioData bioData;

    @ElementCollection(fetch = FetchType.LAZY)
    @CollectionTable(name = "awardsList")
    private List<String> awardsOwned;

    @ManyToMany
    @JoinTable(name = "ONLINE_COURSE_ENROLLMENT",
    joinColumns = @JoinColumn(name = "student_ID", referencedColumnName = "id"),
    inverseJoinColumns = @JoinColumn(name = "course_Name", referencedColumnName = "courseName"))
    private List<TutorialCourse> tutorialCourses;
    //As i can see here , for courses, i used the name instead of ID
    //Since name and Id Both are Unique in my case
    //No Course pf same category will have same name
    //for eg. Two SpringBoot courses are there :




}
