package com.saumrit.myspringbootwithjpa.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.saumrit.myspringbootwithjpa.dto.AssignmentResponseDTO;
import com.saumrit.myspringbootwithjpa.dto.GETStudentResponseDTO;
import com.saumrit.myspringbootwithjpa.dto.POSTStudentRequestDTO;
import com.saumrit.myspringbootwithjpa.dto.StudentWithHouseNumberDetailDto;
import com.saumrit.myspringbootwithjpa.message.producers.StudentProducer;
import com.saumrit.myspringbootwithjpa.model.*;
import com.saumrit.myspringbootwithjpa.model.enums.CourseCategory;
import com.saumrit.myspringbootwithjpa.repository.MyAssignmentRepository;
import com.saumrit.myspringbootwithjpa.repository.MyStudentRepository;
import com.saumrit.myspringbootwithjpa.repository.MySubjectRepository;
import com.saumrit.myspringbootwithjpa.util.CommonConvertorUtil;
import com.saumrit.myspringbootwithjpa.util.UniqueIdGeneratorUtil;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.data.domain.Limit;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;

@Service
public class MyStudentService {

    public final MyStudentRepository myStudentRepository ;
    public final MySubjectRepository mySubjectRepository;
    public final MyAssignmentRepository myAssignmentRepository;
    public final MyTutorialCourseService myTutorialCourseService;
    public final UniqueIdGeneratorUtil uniqueIdGeneratorUtil;
    public final StudentProducer studentProducer;
    public final ObjectMapper objectMapper;

    public MyStudentService(MyStudentRepository myStudentRepository, MySubjectRepository mySubjectRepository, MyAssignmentRepository myAssignmentRepository, MyTutorialCourseService myTutorialCourseService, UniqueIdGeneratorUtil uniqueIdGeneratorUtil, StudentProducer studentProducer, ObjectMapper objectMapper) {
        this.myStudentRepository=myStudentRepository;
        this.mySubjectRepository = mySubjectRepository;
        this.myAssignmentRepository = myAssignmentRepository;
        this.myTutorialCourseService = myTutorialCourseService;
        this.uniqueIdGeneratorUtil = uniqueIdGeneratorUtil;
        this.studentProducer = studentProducer;
        this.objectMapper = objectMapper;
    }


    public void addSingleStudent(POSTStudentRequestDTO POSTStudentRequestDTO){
        Student student= createStudentFromStudentDTO(POSTStudentRequestDTO);
        student.setRollId(uniqueIdGeneratorUtil.generateByApacheText(6));
        myStudentRepository.save(student);
        studentProducer.produceMessageForAddStudent("addstudent-out-0",student);
    }

    public void addMultipleStudent(List<POSTStudentRequestDTO> studentrequestDTOs) {
        studentrequestDTOs.forEach(x -> {
            Student student= createStudentFromStudentDTO(x);
            student.setRollId(uniqueIdGeneratorUtil.generateByApacheText(6));
            myStudentRepository.save(student);
            studentProducer.produceMessageForAddStudent("addstudent-out-0",student);
        });
    }


    public List<GETStudentResponseDTO> fetchAllStudent(){
        List<Student> students=  myStudentRepository.findAll();
        if(!ObjectUtils.isEmpty(students))
            return students.stream()
                    .map(x -> objectMapper.convertValue(x, GETStudentResponseDTO.class))
                    .toList();
        return null;
    }

    public List<GETStudentResponseDTO> fetchAllStudentSortedBy(String sort_property_name){
        Sort sort= Sort.by("age").descending();
        List<Student> students= myStudentRepository.findAll(sort);
        if(!ObjectUtils.isEmpty(students))
            return students.stream()
                    .map(x -> objectMapper.convertValue(x, GETStudentResponseDTO.class))
                    .toList();
        return null;
    }

    public GETStudentResponseDTO fetchAStudentByNameOrRollId(String name , String roll){
        if(null== name && null == roll)
            return null;
        Student student= myStudentRepository.findByNameOrRollId(name, roll);
        return objectMapper.convertValue(student, GETStudentResponseDTO.class);
    }

    public void deleteStudent(String id){
         myStudentRepository.deleteById(id);
    }



    public List<GETStudentResponseDTO> getTheNRIStudentFromThisState(String state){
        List<Student> nriStudents= myStudentRepository.findNRIStudentsFromGivenState(state);
        return nriStudents.stream()
                .map(x -> objectMapper.convertValue(x, GETStudentResponseDTO.class))
                .toList();
    }

    public Student updateSingleStudent(Student student){
        return myStudentRepository.save(student);
    }

    @Transactional
    public AssignmentResponseDTO updateSingleStudentWithAssignmentDetail(String rollId, String subjectName){
        Student student= myStudentRepository.findByNameOrRollId( null,rollId);
        if(ObjectUtils.isEmpty(student))
            return null;
        Subject subject = mySubjectRepository.findByName(subjectName);
        if(ObjectUtils.isEmpty(subject)){
            subject= new Subject();
            subject.setName(subjectName);
            subject=mySubjectRepository.save(subject);
        }
        Assignment assignment= new Assignment();
        assignment.setStudent(student);
        assignment.setSubject(subject);
        myAssignmentRepository.save(assignment);

        return CommonConvertorUtil.assignmentToAssignmentResponseDTO(assignment);
    }

    @Transactional
    public Student updateSingleStudentWithAwardDetail(String rollId, List<String> awards){
        Student student= myStudentRepository.findByNameOrRollId( null,rollId);
        if(ObjectUtils.isEmpty(student))
            return null;
        student.setAwardsOwned(awards);
        Student response= myStudentRepository.save(student);

        return response;
    }

    @Transactional
    public Student updateSingleStudentWithTutoriaCourseDetail(String rollId, String courseName, CourseCategory category){

        Student student= myStudentRepository.findByNameOrRollId( null,rollId);
        if(ObjectUtils.isEmpty(student))
            return null;

        TutorialCourse tutorialCourse= myTutorialCourseService.fetchCourse(courseName);
        if(null == tutorialCourse.getCourseName())
            return null;

        if(null == student.getTutorialCourses())
            student.setTutorialCourses(List.of(tutorialCourse));
        else
            student.getTutorialCourses().add(tutorialCourse);
        return  myStudentRepository.save(student);
    }

    @Transactional
    public int updateTheNRIStatusOFAnyStudent(String roll, boolean status){
        return myStudentRepository.updateTheNRIStatusOFAnyStudent(roll, status);
    }

    public GETStudentResponseDTO getStudentWithAdvanceNameSearch(String name){
        Student student= myStudentRepository.searchStudentWithAdvanceNameSearchWithSpecialCharacterSupport(name, Limit.of(1));
        return objectMapper.convertValue(student, GETStudentResponseDTO.class);
    }

    private Student createStudentFromStudentDTO(POSTStudentRequestDTO POSTStudentRequestDTO){
        Student student= objectMapper.convertValue(POSTStudentRequestDTO, Student.class);
        student.setAddress(objectMapper.convertValue(POSTStudentRequestDTO.getAddressDTO(), Address.class));
        return student;
    }

    public boolean checkIfMinimumPercentForPassingAchievedInThisSubject(String subject){

        return false;
    }

    public void enrollStudentIntoTutorialCourse(){

    }

    public List<StudentWithHouseNumberDetailDto> fetchStudentWithHouseDetails(String city, String state){
        return myStudentRepository.getStudentsFromThisState(city,state)
                .stream().map(x -> {
                    StudentWithHouseNumberDetailDto studentWithHouseNumberDetailDto= new StudentWithHouseNumberDetailDto();
                    studentWithHouseNumberDetailDto.setHouseNumber(x.getAddress().houseRegNumber.toString());
                    studentWithHouseNumberDetailDto.setName(x.getName());
                    studentWithHouseNumberDetailDto.setRoll(x.getRollId());
                    return studentWithHouseNumberDetailDto;
                }).toList();
    }

    public List<StudentWithHouseNumberDetailDto> fetchStudentWithHouseDetailsLeftOuterJoin(String city, String state){
        return myStudentRepository.getStudentsFromThisStateLeftOuterJoin(city,state)
                .stream().map(x -> {
                    StudentWithHouseNumberDetailDto studentWithHouseNumberDetailDto= new StudentWithHouseNumberDetailDto();
                    studentWithHouseNumberDetailDto.setHouseNumber(x.getAddress().houseRegNumber.toString());//here this will be null because of Left outer join
                    studentWithHouseNumberDetailDto.setName(x.getName());
                    studentWithHouseNumberDetailDto.setRoll(x.getRollId());
                    return studentWithHouseNumberDetailDto;
                }).toList();
    }

    public List<Student> getStudentsByNameUsingCriteria(String name){
        return myStudentRepository.giveMeStudentsFromName(name);
    }

    public List<Student> getStudentsByNameAndCityUsingCriteria(String name,String city){
        return myStudentRepository.giveMeStudentsWithNameAndCity(name,city);
    }

    public List<String> getCountriesForStudentsWithCourseName(String courseName){
        return myStudentRepository.fetchCountryForStudentsWithGivenCourseName(courseName);
    }


}
