package com.lisa.studentmanagementsystem.repository;

import com.lisa.studentmanagementsystem.model.Assignment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MyAssignmentRepository extends JpaRepository<Assignment,String> {

}
