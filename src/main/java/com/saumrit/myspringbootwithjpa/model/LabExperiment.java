package com.saumrit.myspringbootwithjpa.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * This class is to show the Derived Entity as Composite Primary key
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@IdClass(value = TheLabExperimentId.class)
@Access(AccessType.FIELD)
public class LabExperiment {

    @Id
    @OneToOne
    @JoinColumn(name = "student_id",referencedColumnName = "id")
    private Student student;

    @Id
    @ManyToOne
    @JoinColumn(name = "subject_id")
    private Subject subject;


    @Id
    @ManyToOne
    @JoinColumn(name = "lecture_id")
    private Lecture guide;

    private LocalDateTime experimentDateTime;


}
