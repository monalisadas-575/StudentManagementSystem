package com.lisa.studentmanagementsystem.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.lisa.studentmanagementsystem.dto.AssignmentResponseDTO;
import com.lisa.studentmanagementsystem.model.Assignment;

public class CommonConvertorUtil {
    public static ObjectMapper objectMapper;

    public static AssignmentResponseDTO assignmentToAssignmentResponseDTO(Assignment assignment){
        AssignmentResponseDTO assignmentResponseDTO= objectMapper.convertValue(assignment, AssignmentResponseDTO.class);
        assignmentResponseDTO.setTimeOfAssignment(assignment.getAssignedDate());
        assignmentResponseDTO.setStudentName(assignmentResponseDTO.getStudentName());
        assignmentResponseDTO.setSubjectName(assignmentResponseDTO.getSubjectName());
        return assignmentResponseDTO;
    }

}
