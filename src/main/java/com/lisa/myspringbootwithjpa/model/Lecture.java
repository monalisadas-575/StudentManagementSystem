package com.lisa.myspringbootwithjpa.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "lecture")
@Access(AccessType.FIELD)
public class Lecture {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    private String name;

    @OneToOne
    @JoinColumn(name = "subject_id",referencedColumnName = "id")
    private Subject subject;

    @OneToMany(mappedBy = "lecture")
    private List<Assignment> theAssignmentsList;
    //Here No @ElementCollection or @CollectionTable is used as Assignment is an entity class
    //And these two annotations are used for non-entity types.
    //But below Certification is non-entity, hence the two annotations are used
    @Embedded
    @ElementCollection
    @CollectionTable
    @AttributeOverrides({
            @AttributeOverride(name = "name",column = @Column(name = "certification_name")),
            @AttributeOverride(name = "authoredBy",column = @Column(name = "issuance_org_name"))
    })
    private List<Certification> certificationList;
}
