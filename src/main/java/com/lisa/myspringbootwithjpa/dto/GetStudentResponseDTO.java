package com.lisa.myspringbootwithjpa.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetStudentResponseDTO {

    public String name;
    //public String standard;
    public Integer age;
    public String city;
}
