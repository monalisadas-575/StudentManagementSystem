package com.saumrit.myspringbootwithjpa.exception;

import com.saumrit.myspringbootwithjpa.exception.exceptions.CourseNotFoundException;
import com.saumrit.myspringbootwithjpa.exception.exceptions.StudentNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(StudentNotFoundException.class)
    public ResponseEntity<String> handleStudentNotFoundException(StudentNotFoundException e){
        return ResponseEntity.badRequest().body(e.getMessage());
    }

    @ExceptionHandler(CourseNotFoundException.class)
    public ResponseEntity<String> handleCourseNotFoundException(CourseNotFoundException e){
        //here no bad request , instead not Found error
        return ResponseEntity.notFound().build();
    }
}
