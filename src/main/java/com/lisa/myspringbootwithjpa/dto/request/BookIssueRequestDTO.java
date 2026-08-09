package com.lisa.myspringbootwithjpa.dto.request;


import com.lisa.myspringbootwithjpa.dto.enums.MemberType;
import jakarta.validation.constraints.NotNull;

public record BookIssueRequestDTO(@NotNull String personId, MemberType type) {
}
