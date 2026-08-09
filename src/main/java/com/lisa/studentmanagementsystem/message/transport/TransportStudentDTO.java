package com.lisa.studentmanagementsystem.message.transport;

import lombok.Data;

@Data
public class TransportStudentDTO {

    private String rollId;
    private String name;
    private String standard;
    private Integer age;

    private TransportAddressDTO address;
}
