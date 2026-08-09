package com.lisa.myspringbootwithjpa.repository;

import com.lisa.myspringbootwithjpa.model.TutorialCourse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MyTutorialCourseRepository extends JpaRepository<TutorialCourse, Long> {
    public TutorialCourse findByCourseName(String courseName);
}
