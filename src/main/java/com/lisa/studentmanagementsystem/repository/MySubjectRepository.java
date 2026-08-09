package com.lisa.studentmanagementsystem.repository;

import com.lisa.studentmanagementsystem.model.Subject;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MySubjectRepository extends JpaRepository<Subject,Long> {
    public Subject findByName(String name);
}
