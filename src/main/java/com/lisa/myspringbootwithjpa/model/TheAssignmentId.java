package com.lisa.myspringbootwithjpa.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Objects;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TheAssignmentId implements Serializable {
    private long student;
    private long subject;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof TheAssignmentId that)) return false;
        return Objects.equals(student, that.student) && Objects.equals(subject, that.subject);
    }

    @Override
    public int hashCode() {
        return Objects.hash(student, subject);
    }
}
