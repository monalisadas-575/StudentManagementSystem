package com.lisa.studentmanagementsystem.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AddressDTO {
    public String city;
    @Min(value = 1,message = "Please provide minimum 1 digit length for HouseNumber")
    public Long houseRegNumber;
    @Size(min = 2,message = "State Code size must be of minimum length 2")
    public String state;
    @Min(value = 10000,message = "Please provide minimum 5 digit length for Zipcode")
    public Integer zipcode;
    @Size(min = 2,message = "Country Code size must be of minimum length 2")
    public String country;
}
