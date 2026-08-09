package com.lisa.myspringbootwithjpa.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class AssignmentResponseDTO {
    public String studentName;
    public String subjectName;
    public LocalDateTime timeOfAssignment;
}
