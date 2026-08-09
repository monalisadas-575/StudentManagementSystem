package com.lisa.myspringbootwithjpa.exception.exceptions;


public class StudentNotFoundException extends RuntimeException {

    public StudentNotFoundException(String message) {
        super(message);
    }


}
