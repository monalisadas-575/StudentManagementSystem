package com.saumrit.myspringbootwithjpa.controller;

import com.saumrit.myspringbootwithjpa.dto.TutorialCourseRequestDTO;
import com.saumrit.myspringbootwithjpa.model.enums.CourseCategory;
import com.saumrit.myspringbootwithjpa.service.MyTutorialCourseService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping( path= "/v1/tutorialcourse")
public class MyTutorialCourseController {

    final MyTutorialCourseService myTutorialCourseService;

    public MyTutorialCourseController(MyTutorialCourseService myTutorialCourseService) {
        this.myTutorialCourseService = myTutorialCourseService;
    }

    @Operation(summary = "Adding course",
            description = "Adding course")
    @PostMapping("/{category}")
    @ResponseStatus(HttpStatus.CREATED)
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",description = "Success"),
            @ApiResponse(responseCode = "400",description = "Bad Request"),
            @ApiResponse(responseCode = "500",description = "Internal Server Error") })
    public void addCourseToSystem(@RequestBody TutorialCourseRequestDTO tutorialCourseRequestDTO, @RequestParam("category") CourseCategory category){
        myTutorialCourseService.addTutorialCourse(tutorialCourseRequestDTO,category);
    }


}
