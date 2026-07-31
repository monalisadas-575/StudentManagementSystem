package com.saumrit.myspringbootwithjpa.controller;

import com.saumrit.myspringbootwithjpa.dto.AssignmentResponseDTO;
import com.saumrit.myspringbootwithjpa.dto.GetStudentResponseDTO;
import com.saumrit.myspringbootwithjpa.dto.PostStudentRequestDTO;
import com.saumrit.myspringbootwithjpa.dto.StudentWithHouseNumberDetailDto;
import com.saumrit.myspringbootwithjpa.model.Student;
import com.saumrit.myspringbootwithjpa.model.enums.CourseCategory;
import com.saumrit.myspringbootwithjpa.service.MyStudentService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/v1/students")
public class MyStudentController {
    public MyStudentService myStudentService;

    public MyStudentController(MyStudentService myStudentService) {
        this.myStudentService = myStudentService;
    }

    @Operation(summary = "Circular Exception in Bidirectional Mapping + @JsonManagedReference + @JsonBackReference",
            description = "Api to add a Student+  Here check the annotation to tackle stackOverFlow Exception ")
    @PostMapping("/addSingleStudent")
    @ResponseStatus(HttpStatus.CREATED)
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",description = "Success"),
            @ApiResponse(responseCode = "400",description = "Bad Request"),
            @ApiResponse(responseCode = "500",description = "Internal Server Error") })
    public void addSingleStudent(@RequestBody PostStudentRequestDTO POSTStudentRequestDTO){
        myStudentService.addSingleStudent(POSTStudentRequestDTO);
    }

    @Operation(summary = "Circular Exception in Bidirectional Mapping + @JsonManagedReference + @JsonBackReference",
            description = "Api to add a Student+  Here check the annotation to tackle stackOverFlow Exception ")
    @PostMapping("/addManyStudent")
    @ResponseStatus(HttpStatus.CREATED)
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",description = "Success"),
            @ApiResponse(responseCode = "400",description = "Bad Request"),
            @ApiResponse(responseCode = "500",description = "Internal Server Error") })
    public void addmanyStudent(@RequestBody List<PostStudentRequestDTO> allStudents){
        myStudentService.addMultipleStudent(allStudents);
    }

    @Operation(summary = "Api to get All Students",
    description = "Api to get All Students")
    @GetMapping("/getAllStudents")
    @ResponseStatus(HttpStatus.OK)
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",description = "Success"),
            @ApiResponse(responseCode = "400",description = "Bad Request"),
            @ApiResponse(responseCode = "500",description = "Internal Server Error") })
    public List<GetStudentResponseDTO> getAllStudents(){
        return myStudentService.fetchAllStudent();
    }

    @Operation(summary = "Api to get All Students From A certain Address",
            description = "Api to get All Students From A certain Address")
    @GetMapping("/getAllStudentsFromCertainAddress")
    @ResponseStatus(HttpStatus.OK)
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",description = "Success"),
            @ApiResponse(responseCode = "400",description = "Bad Request"),
            @ApiResponse(responseCode = "500",description = "Internal Server Error") })
    public List<StudentWithHouseNumberDetailDto> getAllStudentsWithAddressDetail(@RequestParam("city") String city, @RequestParam("state") String state){
        return myStudentService.fetchStudentWithHouseDetails(city,state);
    }

    @Operation(summary = "Api to get All Students From A certain Address LEFT OUTER JOIN",
            description = "Api to get All Students From A certain Address LEFT OUTER JOIN Demo")
    @GetMapping("/getAllStudentsFromCertainAddressOuterJoin")
    @ResponseStatus(HttpStatus.OK)
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",description = "Success"),
            @ApiResponse(responseCode = "400",description = "Bad Request"),
            @ApiResponse(responseCode = "500",description = "Internal Server Error") })
    public List<StudentWithHouseNumberDetailDto> getAllStudentsWithAddressDetailLeftOuterJoin(@RequestParam("city") String city, @RequestParam("state") String state){
        return myStudentService.fetchStudentWithHouseDetailsLeftOuterJoin(city,state);
    }

    @Operation(summary = "Api to get All Students",
            description = "Api to get All Students")
    @GetMapping("/getAllNRIStudents")
    @ResponseStatus(HttpStatus.OK)
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",description = "Success"),
            @ApiResponse(responseCode = "400",description = "Bad Request"),
            @ApiResponse(responseCode = "500",description = "Internal Server Error") })
    public List<GetStudentResponseDTO> getAllNRIStudentsOfSpecificState(@RequestParam String stateName){
        return myStudentService.getTheNRIStudentFromThisState(stateName);
    }

    @Operation(summary = "Api to get All Students with Sorting applied",
            description = "Api to get All Students in a sorted Order")
    @GetMapping("/getAllStudentsSortedBy")
    @ResponseStatus(HttpStatus.OK)
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",description = "Success"),
            @ApiResponse(responseCode = "400",description = "Bad Request"),
            @ApiResponse(responseCode = "500",description = "Internal Server Error") })
    public List<GetStudentResponseDTO> getAllStudentsSortedBy(@RequestParam String sortPropertyName){
        return myStudentService.fetchAllStudentSortedBy(sortPropertyName);
    }

    @Operation(summary = "Api to get A Student With Special character Support",
            description = "Api to get A Student With Special character Support")
    @GetMapping("/advanceSearchForStudent")
    @ResponseStatus(HttpStatus.OK)
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",description = "Success"),
            @ApiResponse(responseCode = "400",description = "Bad Request"),
            @ApiResponse(responseCode = "500",description = "Internal Server Error") })
    public GetStudentResponseDTO advanceSearchForStudent(@RequestParam String sortPropertyName){
        return myStudentService.getStudentWithAdvanceNameSearch(sortPropertyName);
    }

    @Operation(summary = "Api to get a Student by searching with name or rollID",
            description = "Api to get a Student by searching with name or rollID")
    @GetMapping("/getAStudentByNameOrRollId")
    @ResponseStatus(HttpStatus.OK)
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",description = "Success"),
            @ApiResponse(responseCode = "400",description = "Bad Request"),
            @ApiResponse(responseCode = "500",description = "Internal Server Error") })
    public GetStudentResponseDTO getAStudentByNameORRollNumber(@RequestParam String name, @RequestParam String roll){
        return myStudentService.fetchAStudentByNameOrRollId(name,roll);
    }

    @Operation(summary = "Api to Update NRI Status of a Student",
            description = "Api to Update NRI Status of a Student")
    @PutMapping("/NRI-status")
    @ResponseStatus(HttpStatus.OK)
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",description = "Success"),
            @ApiResponse(responseCode = "400",description = "Bad Request"),
            @ApiResponse(responseCode = "500",description = "Internal Server Error") })
    public int  updateNRIStatusForSingleStudent(@RequestParam String roll,@RequestParam boolean status){
        return myStudentService.updateTheNRIStatusOFAnyStudent(roll, status);
    }

    @Operation(summary = "Api to remove a Student",
            description = "Api to remove a Student")
    @DeleteMapping("/{id}/removeStudent")
    @ResponseStatus(HttpStatus.OK)
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",description = "Success"),
            @ApiResponse(responseCode = "400",description = "Bad Request"),
            @ApiResponse(responseCode = "500",description = "Internal Server Error") })
    public void deleteSingleStudent(@PathVariable String id){
        myStudentService.deleteStudent(id);
    }

    @Operation(summary = "Api to Update a Student",
            description = "Api to Update a Student")
    @PutMapping("/updateStudent")
    @ResponseStatus(HttpStatus.OK)
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",description = "Success"),
            @ApiResponse(responseCode = "400",description = "Bad Request"),
            @ApiResponse(responseCode = "500",description = "Internal Server Error") })
    public Student updateSingleStudent(@RequestBody Student student){
        return myStudentService.updateSingleStudent(student);
    }

    @Operation(summary = "Api to patch update a Student",
            description = "Api to patch update a Student")
    @PatchMapping("/patchStudentInformation")
    @ResponseStatus(HttpStatus.OK)
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",description = "Success"),
            @ApiResponse(responseCode = "400",description = "Bad Request"),
            @ApiResponse(responseCode = "500",description = "Internal Server Error") })
    public Student patchUpdateSingleStudent(@RequestBody Student student){
        return myStudentService.updateSingleStudent(student);
    }

    @Operation(summary = "Here composite Primary Key is explained through Assignment",
            description = "Api to give assignments to a Student")
    @PatchMapping("/assignment/{rollId}")
    @ResponseStatus(HttpStatus.OK)
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",description = "Success"),
            @ApiResponse(responseCode = "400",description = "Bad Request"),
            @ApiResponse(responseCode = "500",description = "Internal Server Error") })
    public AssignmentResponseDTO assignmentToStudent(@PathVariable("rollId") String rollId, @RequestParam("subject") String subject){
        return myStudentService.updateSingleStudentWithAssignmentDetail(rollId,subject);
    }


    @Operation(summary = "Here @ElementCollection/@CollectionTable is used",
            description = "Api to give assignments to a Student")
    @PatchMapping("/award/{rollId}")
    @ResponseStatus(HttpStatus.OK)
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",description = "Success"),
            @ApiResponse(responseCode = "400",description = "Bad Request"),
            @ApiResponse(responseCode = "500",description = "Internal Server Error") })
    public Student awardsToStudent(@PathVariable("rollId") String rollId,@RequestBody List<String> awards){
        return myStudentService.updateSingleStudentWithAwardDetail(rollId,awards);
    }

    @Operation(summary = "Here @Jointable is used",
            description = "Api to give assignments to a Student")
    @PatchMapping("/tutorialCourse/{rollId}/{name}")
    @ResponseStatus(HttpStatus.OK)
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",description = "Success"),
            @ApiResponse(responseCode = "400",description = "Bad Request"),
            @ApiResponse(responseCode = "500",description = "Internal Server Error") })
    public Student enrollStudentToTutorialCourse(@PathVariable("rollId") String rollId, @PathVariable("name") String courseName,
                                                 @RequestParam("category")CourseCategory category){
        return myStudentService.updateSingleStudentWithTutoriaCourseDetail(rollId,courseName,category);
    }







}
