package com.lisa.studentmanagementsystem.dto.request;


import com.lisa.studentmanagementsystem.dto.enums.MemberType;
import jakarta.validation.constraints.NotNull;

public record BookIssueRequestDTO(@NotNull String personId, MemberType type) {
}
