package com.saumrit.myspringbootwithjpa.dto.request;


import com.saumrit.myspringbootwithjpa.dto.enums.MemberType;

import java.util.List;

public record BookReturnRequestDTO(String personId, String bookname, MemberType type, List<String> ISBNs) {
}
