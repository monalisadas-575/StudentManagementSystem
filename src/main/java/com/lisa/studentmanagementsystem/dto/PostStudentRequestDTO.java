package com.lisa.studentmanagementsystem.dto;


import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PostStudentRequestDTO {

    @NotNull
    public String name;
    public String standard;
    public Integer age;
    public AddressDTO addressDTO;
//    public Assignment assignment;
//    private BioData bioData;
//    private List<String> awardsOwned;
    //private List<TutorialCourse> tutorialCourses;



}
