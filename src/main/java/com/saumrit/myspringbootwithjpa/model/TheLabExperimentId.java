package com.saumrit.myspringbootwithjpa.model;


import lombok.AllArgsConstructor;
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
