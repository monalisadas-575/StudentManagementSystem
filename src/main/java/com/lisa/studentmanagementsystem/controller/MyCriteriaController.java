package com.lisa.studentmanagementsystem.controller;

import com.lisa.studentmanagementsystem.dto.CityStudentCountDTO;
import com.lisa.studentmanagementsystem.dto.GetStudentResponseDTO;
import com.lisa.studentmanagementsystem.model.Student;
import com.lisa.studentmanagementsystem.service.MyStudentService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/criteria")
public class MyCriteriaController {

    public final MyStudentService studentService;

    public MyCriteriaController(MyStudentService studentService) {
        this.studentService = studentService;
    }

    @Operation(summary = "Fetch Students with given name using Criteria API",
            description = "Fetch Students with given name using Criteria API")
    @GetMapping("/students/{name}")
    @ResponseStatus(HttpStatus.OK)
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",description = "Success"),
            @ApiResponse(responseCode = "400",description = "Bad Request"),
            @ApiResponse(responseCode = "500",description = "Internal Server Error") })
    public List<Student> getStudentsByNameUsingCriteria(@PathVariable("name") String name){
        return studentService.getStudentsByNameUsingCriteria(name);

    }

    @Operation(summary = "Fetch Students with given name and city using Criteria API",
            description = "Fetch Students with given name and city using Criteria API")
    @GetMapping("/students/{name}/{city}")
    @ResponseStatus(HttpStatus.OK)
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",description = "Success"),
            @ApiResponse(responseCode = "400",description = "Bad Request"),
            @ApiResponse(responseCode = "500",description = "Internal Server Error") })
    public List<Student> getStudentsByNameAndCityUsingCriteria(@PathVariable("name") String name, @PathVariable("city") String city ){
        return studentService.getStudentsByNameAndCityUsingCriteria(name,city);

    }

    @Operation(summary = "Fetch Country for the Students with given course name",
            description = "Fetch Country for the Students with given course name using Criteria API")
    @GetMapping("/students/course/{coursename}")
    @ResponseStatus(HttpStatus.OK)
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",description = "Success"),
            @ApiResponse(responseCode = "400",description = "Bad Request"),
            @ApiResponse(responseCode = "500",description = "Internal Server Error") })
    public List<String> getStudentsByNameAndCityUsingCriteria(@PathVariable("coursename") String coursename){
        return studentService.getCountriesForStudentsWithCourseName(coursename);

    }

    @Operation(summary = "Fetch Student with their Name , Age and City from Given Country",
            description = "Fetch Student with their Name , Age and City from Given Country using Criteria API")
    @GetMapping("/students/detail/{country}")
    @ResponseStatus(HttpStatus.OK)
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",description = "Success"),
            @ApiResponse(responseCode = "400",description = "Bad Request"),
            @ApiResponse(responseCode = "500",description = "Internal Server Error") })
    public List<GetStudentResponseDTO> getStudentDetailsWithCountryName(@PathVariable("country") String country){
        return studentService.getStudentsWithCityDetailFromGivenCountry(country);
    }

    @Operation(summary = "Update Student;s age to 30, from Given City",
            description = "Update Student;s age to 30, from Given City using Criteria API")
    @PatchMapping("/students/age/{city}")
    @ResponseStatus(HttpStatus.OK)
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",description = "Success"),
            @ApiResponse(responseCode = "400",description = "Bad Request"),
            @ApiResponse(responseCode = "500",description = "Internal Server Error") })
    public Integer updateAgeToThirty(@PathVariable("city") String city){
        return studentService.updateStudentAgeToThirtyFromThisCity(city);
    }

    @Operation(summary = "Delete student by its name",
            description = "Delete student by its name")
    @DeleteMapping("/students/delete/{name}")
    @ResponseStatus(HttpStatus.OK)
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",description = "Success"),
            @ApiResponse(responseCode = "400",description = "Bad Request"),
            @ApiResponse(responseCode = "500",description = "Internal Server Error") })
    public Integer deleteStudentByName(@PathVariable("name") String name){
        return studentService.deleteStudentByName(name);
    }

    @Operation(summary = "Fetch student name & city order by student name length",
            description = "Fetch student name & city order by student name length")
    @GetMapping("/students/NameLength/{city}")
    @ResponseStatus(HttpStatus.OK)
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",description = "Success"),
            @ApiResponse(responseCode = "400",description = "Bad Request"),
            @ApiResponse(responseCode = "500",description = "Internal Server Error") })
    public List<GetStudentResponseDTO> fetchStudentByNameLength(@PathVariable("city") String city){
        return studentService.fetchStudentByNameLength(city);
    }

    @Operation(summary = "Fetch student count & city grouping by city & having student count more than 1",
            description = "Fetch student count & city grouping by city & having student count more than 1")
    @GetMapping("/students/city/student-count")
    @ResponseStatus(HttpStatus.OK)
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",description = "Success"),
            @ApiResponse(responseCode = "400",description = "Bad Request"),
            @ApiResponse(responseCode = "500",description = "Internal Server Error") })
    public List<CityStudentCountDTO> fetchStudentCountBasedOnCity(){
        return studentService.getCityWithStudentCountMoreThanTwo();
    }



}
