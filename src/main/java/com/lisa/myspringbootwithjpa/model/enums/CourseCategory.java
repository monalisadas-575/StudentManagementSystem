package com.lisa.myspringbootwithjpa.model.enums;

public enum CourseCategory {
    SPRING_BOOT("Spring-Boot"),
    CORE_JAVA("The core java 17");

    final String description;

    CourseCategory(String description) {
        this.description = description;
    }
}
