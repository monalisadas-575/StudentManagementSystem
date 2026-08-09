package com.lisa.studentmanagementsystem.dto.request;


import com.lisa.studentmanagementsystem.dto.enums.MemberType;

import java.util.List;

public record BookReturnRequestDTO(String personId, String bookname, MemberType type, List<String> ISBNs) {
}
