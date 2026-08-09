package com.lisa.studentmanagementsystem.message.transport;

import jakarta.persistence.Column;
import lombok.Data;

@Data
public class TransportAddressDTO {

    public String city;

    @Column(unique = true)
    public Long houseRegNumber;
    public String state;
    public Integer zipcode;
    public String country;
}
