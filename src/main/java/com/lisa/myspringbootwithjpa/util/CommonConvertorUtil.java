package com.lisa.myspringbootwithjpa.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.lisa.myspringbootwithjpa.dto.AssignmentResponseDTO;
import com.lisa.myspringbootwithjpa.model.Assignment;

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
