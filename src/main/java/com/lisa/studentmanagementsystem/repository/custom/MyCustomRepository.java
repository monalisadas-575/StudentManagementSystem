package com.lisa.studentmanagementsystem.repository.custom;

import com.lisa.studentmanagementsystem.model.Student;
import jakarta.persistence.Tuple;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MyCustomRepository {

    List<Student> giveMeStudentsFromName(String name);

    List<Student> giveMeStudentsWithNameAndCity(String name,String city);

    List<String> fetchCountryForStudentsWithGivenCourseName(String courseName);

    List<Tuple> fetchStudentWithTheirCity(String country);

    Integer updateAgeByTwoForStudentsFromThisCity(String city);

    Integer deleteStudentByStudentName(String name);

    List<Tuple> fetchStudentWithCityBasedOnNameLengthFromCity(String city);

    List<Tuple> getCityWithStudentCountMoreThanTwo( );




//    Integer deleteStudent
}
