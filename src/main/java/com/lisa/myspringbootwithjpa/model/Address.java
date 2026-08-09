package com.lisa.myspringbootwithjpa.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;


@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString(exclude = "theStudent")//this is added on  to avoid stackOverflow due to circular dependency issue
@Entity(name = "address")//just for trial, i used this annotation, no need of this
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    public long id;
    public String city;

    @Column
    public Long houseRegNumber;
    public String state;
    public String zipcode;
    public String country;
    @OneToOne(mappedBy = "address")
    @JsonManagedReference
    public Student theStudent;
    //here if i do not use this annotation , i will get stackOverFLow exception during toString() as student has address which has student which has address ......
}
