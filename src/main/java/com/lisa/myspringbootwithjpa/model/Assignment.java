package com.lisa.myspringbootwithjpa.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;

/**
 * This class is to show the Composite primary key using Id-class
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "assignment")
@Access(AccessType.FIELD)
@IdClass(value = TheAssignmentId.class)
public class Assignment {

    @Id
    @ManyToOne
    private Student student;//here data type is Student instead of Long
    @Id
    @ManyToOne
    private Subject subject;//same as above

    @Column(name = "assigned_date")
    private LocalDateTime assignedDate= Instant.now().atZone(ZoneId.systemDefault()).toLocalDateTime();

    @ManyToOne
    @JoinColumn(name = "lecture_id",referencedColumnName = "id")
    private Lecture lecture;
}
