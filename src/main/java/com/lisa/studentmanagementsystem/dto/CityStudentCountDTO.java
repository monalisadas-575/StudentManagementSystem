package com.lisa.studentmanagementsystem.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CityStudentCountDTO {
    private String city;
    private Long studentCount;

}
