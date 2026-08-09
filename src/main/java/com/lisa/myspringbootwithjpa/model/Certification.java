package com.lisa.myspringbootwithjpa.model;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Embeddable
public class Certification {
    private String name;
    private String authorizedBy;
    private Long price;
}
