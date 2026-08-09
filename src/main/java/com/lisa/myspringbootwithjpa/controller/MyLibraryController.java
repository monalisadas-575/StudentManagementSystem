package com.lisa.myspringbootwithjpa.controller;

import com.lisa.myspringbootwithjpa.dto.request.BookIssueRequestDTO;
import com.lisa.myspringbootwithjpa.service.MyLibraryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/library")
public class MyLibraryController {

    public final Logger logger= LoggerFactory.getLogger(MyLibraryController.class);

    public MyLibraryService myLibraryService;

    public MyLibraryController(MyLibraryService myLibraryService) {
        this.myLibraryService = myLibraryService;
    }

    @Operation(summary = "Controller to issue new Books for the Student",
            description = "Controller to issue new Books for the Student")
    @PostMapping("/issue/{name}/{category}")
    @ResponseStatus(HttpStatus.CREATED)
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201",description = "Success"),
            @ApiResponse(responseCode = "400",description = "Not Found"),
            @ApiResponse(responseCode = "500",description = "Internal Server Error") })
    public void issueBooks(@RequestParam(name="bookCount") Integer bookCount,
                           @PathVariable(name = "category") String bookCategory,
                           @PathVariable(name= "name") String bookName,
                           @RequestBody BookIssueRequestDTO bookIssueRequestDTO){
        myLibraryService.issueBook(bookName,bookCategory,bookCount,bookIssueRequestDTO);

    }


}
