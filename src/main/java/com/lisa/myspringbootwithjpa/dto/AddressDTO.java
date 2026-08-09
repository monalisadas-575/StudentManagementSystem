package com.lisa.myspringbootwithjpa.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AddressDTO {
    public String city;
    public Long houseRegNumber;
    public String state;
    public Integer zipcode;
    public String country;
}
