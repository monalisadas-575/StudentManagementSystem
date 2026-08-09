package com.lisa.myspringbootwithjpa.repository;

import com.lisa.myspringbootwithjpa.model.Assignment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MyAssignmentRepository extends JpaRepository<Assignment,String> {

}
