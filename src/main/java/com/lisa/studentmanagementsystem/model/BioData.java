package com.lisa.studentmanagementsystem.model;

import com.lisa.studentmanagementsystem.model.enums.Hobby;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Embeddable
public class BioData {

    private String fathersName;
    private String mothersName;
    private Integer numberOfSiblings;
    private Boolean nriStatus;
    private LocalDateTime dateOfBirth;
    @Enumerated(EnumType.STRING)
    @ElementCollection
    @CollectionTable
    private List<Hobby> hobbies;

}
