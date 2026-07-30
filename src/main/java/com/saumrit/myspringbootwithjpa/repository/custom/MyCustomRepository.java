package com.saumrit.myspringbootwithjpa.repository.custom;

import com.saumrit.myspringbootwithjpa.model.Student;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MyCustomRepository {

    List<Student> giveMeStudentsFromName(String name);

    List<Student> giveMeStudentsWithNameAndCity(String name,String city);

    List<String> fetchCountryForStudentsWithGivenCourseName(String courseName);
}
