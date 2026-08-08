package com.saumrit.myspringbootwithjpa.dto.request;


import com.saumrit.myspringbootwithjpa.dto.enums.MemberType;
import jakarta.validation.constraints.NotNull;

public record BookIssueRequestDTO(@NotNull String personId, MemberType type) {
}
