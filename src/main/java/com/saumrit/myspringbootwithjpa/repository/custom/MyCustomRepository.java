package com.saumrit.myspringbootwithjpa.repository.custom;

import com.saumrit.myspringbootwithjpa.model.Student;
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
