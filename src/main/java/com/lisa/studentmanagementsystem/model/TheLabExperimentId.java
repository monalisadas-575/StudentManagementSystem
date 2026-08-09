package com.lisa.studentmanagementsystem.model;


import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;


@Data
@NoArgsConstructor
public class TheLabExperimentId implements Serializable {


    private Long student;
    private Long subject;
    private Long guide;




}
