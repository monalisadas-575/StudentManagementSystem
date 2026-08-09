package com.lisa.myspringbootwithjpa.repository;

import com.lisa.myspringbootwithjpa.model.Subject;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MySubjectRepository extends JpaRepository<Subject,Long> {
    public Subject findByName(String name);
}
