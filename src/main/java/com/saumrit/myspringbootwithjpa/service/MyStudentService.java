package com.saumrit.myspringbootwithjpa.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.saumrit.myspringbootwithjpa.dto.*;
import com.saumrit.myspringbootwithjpa.exception.exceptions.CourseNotFoundException;
import com.saumrit.myspringbootwithjpa.exception.exceptions.StudentNotFoundException;
import com.saumrit.myspringbootwithjpa.message.producers.StudentProducer;
import com.saumrit.myspringbootwithjpa.model.*;
import com.saumrit.myspringbootwithjpa.model.enums.CourseCategory;
import com.saumrit.myspringbootwithjpa.repository.MyAssignmentRepository;
import com.saumrit.myspringbootwithjpa.repository.MyStudentRepository;
import com.saumrit.myspringbootwithjpa.repository.MySubjectRepository;
import com.saumrit.myspringbootwithjpa.util.CommonConvertorUtil;
import com.saumrit.myspringbootwithjpa.util.UniqueIdGeneratorUtil;
import jakarta.persistence.Tuple;
import org.apache.commons.lang3.ObjectUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Limit;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class MyStudentService {

    public static Logger logger = LoggerFactory.getLogger(MyStudentService.class);

    public final MyStudentRepository myStudentRepository;
    public final MySubjectRepository mySubjectRepository;
    public final MyAssignmentRepository myAssignmentRepository;
    public final MyTutorialCourseService myTutorialCourseService;
    public final UniqueIdGeneratorUtil uniqueIdGeneratorUtil;
    public final StudentProducer studentProducer;
    public final ObjectMapper objectMapper;

    public MyStudentService(MyStudentRepository myStudentRepository, MySubjectRepository mySubjectRepository, MyAssignmentRepository myAssignmentRepository, MyTutorialCourseService myTutorialCourseService, UniqueIdGeneratorUtil uniqueIdGeneratorUtil, StudentProducer studentProducer, ObjectMapper objectMapper) {
        this.myStudentRepository = myStudentRepository;
        this.mySubjectRepository = mySubjectRepository;
        this.myAssignmentRepository = myAssignmentRepository;
        this.myTutorialCourseService = myTutorialCourseService;
        this.uniqueIdGeneratorUtil = uniqueIdGeneratorUtil;
        this.studentProducer = studentProducer;
        this.objectMapper = objectMapper;
    }


    public void addSingleStudent(PostStudentRequestDTO POSTStudentRequestDTO) {
        logger.info("Inside the addSingleStudent()");
        logger.debug("Entered into addSingleStudent() with requestDTO as {}", POSTStudentRequestDTO);
        Student student = createStudentFromStudentDTO(POSTStudentRequestDTO);
        student.setRollId(uniqueIdGeneratorUtil.generateByApacheText(6));
        logger.debug("Value of Student that i got from DB is {} with RollID: {}", student, student.getRollId());
        myStudentRepository.save(student);
        studentProducer.produceMessageForAddStudent("addstudent-out-0", student);
    }

    public void addSingleStudent(PostStudentRequestDTO POSTStudentRequestDTO, String name) {
        logger.info("Inside the addSingleStudent()");
        logger.debug("Entered into addSingleStudent() with requestDTO as {}", POSTStudentRequestDTO);
        Student student = createStudentFromStudentDTO(POSTStudentRequestDTO);
        student.setRollId(uniqueIdGeneratorUtil.generateByApacheText(6));
        logger.debug("Value of Student that i got from DB is {} with RollID: {}", student, student.getRollId());
        myStudentRepository.save(student);
        studentProducer.produceMessageForAddStudent("addstudent-out-0", student);
    }

    public void addMultipleStudent(List<PostStudentRequestDTO> studentrequestDTOs) {
        logger.info("Inside the addMultipleStudent()");
        logger.debug("Entered into addMultipleStudent() with requestDTO as {}", studentrequestDTOs);
        studentrequestDTOs.forEach(x -> {
            Student student = createStudentFromStudentDTO(x);
            student.setRollId(uniqueIdGeneratorUtil.generateByApacheText(6));
            logger.debug("Value of Student from DB is {} with RollId: {}", student, student.getRollId());
            myStudentRepository.save(student);
            studentProducer.produceMessageForAddStudent("addstudent-out-0", student);
        });
    }


    public List<GetStudentResponseDTO> fetchAllStudent() {
        logger.info("Inside fetchAllStudent()");
        List<Student> students = myStudentRepository.findAll();
        logger.debug("Fetching {} number from myStudentRepository ", students.size());
        if (!ObjectUtils.isEmpty(students)) {
            logger.debug("Students fetched from DB are: {}", students);
            List<GetStudentResponseDTO> list = students.stream()
                    .map(x -> objectMapper.convertValue(x, GetStudentResponseDTO.class))
                    .toList();
            logger.debug("Successfully converted {} students into GetStudentResponseDTO", list.size());
            return list;

        }
        logger.info("No students found in database");
        return null;
    }

    public List<GetStudentResponseDTO> fetchAllStudentSortedBy(String sort_property_name) {
        logger.info("inside the fetchAllStudentSortedBy()");
        logger.debug("fetching students sorted by property: {}", sort_property_name);
        Sort sort = Sort.by("age").descending();
        List<Student> students = myStudentRepository.findAll(sort);
        if (!ObjectUtils.isEmpty(students)) {
            List<GetStudentResponseDTO> list = students.stream()
                    .map(x -> objectMapper.convertValue(x, GetStudentResponseDTO.class))
                    .toList();
            logger.debug("Successfully converted {} students into GetStudentResponseDTO", list.size());
            return list;
        }
        logger.info("No students found while fetching");
        return null;
    }

    public GetStudentResponseDTO fetchAStudentByNameOrRollId(String name, String roll) {
        logger.info("Entered into fetchAStudentByNameOrRollId()");
        logger.debug("Fetching Student by this name: {} & RollId: {}", name, roll);
        if (null == name && null == roll) {
            logger.info("Both name & rooId are null so cant fetch student");
            return null;
        }
        Student student = myStudentRepository.findByNameOrRollId(name, roll);
        logger.debug("Fetched student {} from studentRepository ", student);
        return objectMapper.convertValue(student, GetStudentResponseDTO.class);
    }

    public void deleteStudent(String id) {
        logger.info("Inside the deleteStudent()");
        logger.debug("Deleting student with this id: {}", id);
        myStudentRepository.deleteById(id);
    }


    public List<GetStudentResponseDTO> getTheNRIStudentFromThisState(String state) {
        logger.info("Indide the getTheNRIStudentFromThisState()");
        logger.debug("Fetching NRI student from {} state", state);
        List<Student> nriStudents = myStudentRepository.findNRIStudentsFromGivenState(state);
        logger.debug("Successfully fetched {} no of NRI students as shown {}", nriStudents.size(), nriStudents);
        return nriStudents.stream()
                .map(x -> objectMapper.convertValue(x, GetStudentResponseDTO.class))
                .toList();
    }

    public Student updateSingleStudent(Student student) {
        logger.info("Inside the updateSingleStudent");
        logger.debug("Student received for update {}", student);
        Student updatedStudent = myStudentRepository.save(student);
        logger.debug("Successfully updated student is: {} ", updatedStudent);
        return updatedStudent;
    }

    @Transactional
    public AssignmentResponseDTO updateSingleStudentWithAssignmentDetail(String rollId, String subjectName) {
        logger.info("Inside the updateSingleStudentWithAssignmentDetail()");
        logger.debug("Updating Student AssignmentDetails matching with rollId:{} with a new subject: {}", rollId, subjectName);
        Student student = myStudentRepository.findByNameOrRollId(null, rollId);
        if (ObjectUtils.isEmpty(student)) {
            logger.error("No Student Found with this rollID: {}", rollId);
            throw new StudentNotFoundException("Student is not present in the System with Provided name and RollId combination");
        }
        Subject subject = mySubjectRepository.findByName(subjectName);
        if (ObjectUtils.isEmpty(subject)) {
            subject = new Subject();
            subject.setName(subjectName);
            subject = mySubjectRepository.save(subject);
            logger.debug("Adding the Subject {}", subjectName);
        }
        Assignment assignment = new Assignment();
        assignment.setStudent(student);
        assignment.setSubject(subject);
        myAssignmentRepository.save(assignment);
        logger.debug("Saved Student's Assignment  with the SubjectName {} ", subjectName);

        return CommonConvertorUtil.assignmentToAssignmentResponseDTO(assignment);
    }

    @Transactional
    public Student updateSingleStudentWithAwardDetail(String rollId, List<String> awards) {
        Student student = myStudentRepository.findByNameOrRollId(null, rollId);

        if (ObjectUtils.isEmpty(student)) {
            logger.error("No Student Found with this rollID: {}", rollId);
            throw new StudentNotFoundException("Student is not present in the System with  given RollId");
        }
        student.setAwardsOwned(awards);
        Student response = myStudentRepository.save(student);
        logger.debug("Successfully Updated the Awards for the student with RollId: {}", rollId);
        return response;
    }

    @Transactional
    public Student updateSingleStudentWithTutoriaCourseDetail(String rollId, String courseName, CourseCategory category) {

        Student student = myStudentRepository.findByNameOrRollId(null, rollId);
        if (ObjectUtils.isEmpty(student)) {
            logger.error("No Student Found with this rollID: {}", rollId);
            throw new StudentNotFoundException("Student is not present in the System with  given RollId");
        }

        TutorialCourse tutorialCourse = myTutorialCourseService.fetchCourse(courseName);
        if (null == tutorialCourse.getCourseName()) {
            logger.error("No Course Found with given name {}", courseName);
            throw new CourseNotFoundException("No such Course Found");
        }

        if (null == student.getTutorialCourses()) {
            logger.info("Student did not have any course earlier, Adding the course newly");
            student.setTutorialCourses(List.of(tutorialCourse));
        } else
            student.getTutorialCourses().add(tutorialCourse);
        return myStudentRepository.save(student);
    }

    @Transactional
    public int updateTheNRIStatusOFAnyStudent(String roll, boolean status) {
        int i = 0;
        try {
            i = myStudentRepository.updateTheNRIStatusOFAnyStudent(roll, status);
            logger.debug("Total Number of affected Students is {}", i);
        } catch (Exception e) {
            //no Null check done earlier for the Student. let DB throw RunTime Error when no Student found.
            logger.error("Error happened while updating the student's NRI status");
            throw new RuntimeException(e);
        }
        return i;
    }

    public GetStudentResponseDTO getStudentWithAdvanceNameSearch(String name) {
        logger.debug("Searching for Student with name {}", name);
        Student student = null;
        try {
            student = myStudentRepository.searchStudentWithAdvanceNameSearchWithSpecialCharacterSupport(name, Limit.of(1));
        } catch (Exception e) {
            logger.error("Some Error occurred in Search: {}", e.getMessage());
            throw new RuntimeException(e.getMessage());
        }
        return objectMapper.convertValue(student, GetStudentResponseDTO.class);
    }

    private Student createStudentFromStudentDTO(PostStudentRequestDTO POSTStudentRequestDTO) {
        Student student = objectMapper.convertValue(POSTStudentRequestDTO, Student.class);
        student.setAddress(objectMapper.convertValue(POSTStudentRequestDTO.getAddressDTO(), Address.class));
        return student;
    }

    public boolean checkIfMinimumPercentForPassingAchievedInThisSubject(String subject) {
        return false;
    }

    public void enrollStudentIntoTutorialCourse() {

    }

    public List<StudentWithHouseNumberDetailDto> fetchStudentWithHouseDetails(String city, String state) {
        return myStudentRepository.getStudentsFromThisState(city, state)
                .stream().map(x -> {
                    StudentWithHouseNumberDetailDto studentWithHouseNumberDetailDto = new StudentWithHouseNumberDetailDto();
                    studentWithHouseNumberDetailDto.setHouseNumber(x.getAddress().houseRegNumber.toString());
                    studentWithHouseNumberDetailDto.setName(x.getName());
                    studentWithHouseNumberDetailDto.setRoll(x.getRollId());
                    return studentWithHouseNumberDetailDto;
                }).toList();
    }

    public List<StudentWithHouseNumberDetailDto> fetchStudentWithHouseDetailsLeftOuterJoin(String city, String state) {
        return myStudentRepository.getStudentsFromThisStateLeftOuterJoin(city, state)
                .stream().map(x -> {
                    StudentWithHouseNumberDetailDto studentWithHouseNumberDetailDto = new StudentWithHouseNumberDetailDto();
                    studentWithHouseNumberDetailDto.setHouseNumber(x.getAddress().houseRegNumber.toString());//here this will be null because of Left outer join
                    studentWithHouseNumberDetailDto.setName(x.getName());
                    studentWithHouseNumberDetailDto.setRoll(x.getRollId());
                    return studentWithHouseNumberDetailDto;
                }).toList();
    }

    public List<Student> getStudentsByNameUsingCriteria(String name) {
        return myStudentRepository.giveMeStudentsFromName(name);
    }

    public List<Student> getStudentsByNameAndCityUsingCriteria(String name, String city) {
        return myStudentRepository.giveMeStudentsWithNameAndCity(name, city);
    }

    public List<String> getCountriesForStudentsWithCourseName(String courseName) {
        return myStudentRepository.fetchCountryForStudentsWithGivenCourseName(courseName);
    }

    public List<GetStudentResponseDTO> getStudentsWithCityDetailFromGivenCountry(String countryName) {

        logger.info("Searching for Student From this country {}", countryName);
        List<Tuple> results = myStudentRepository.fetchStudentWithTheirCity(countryName);
        List<GetStudentResponseDTO> myResult = results.stream().map(x -> {
            GetStudentResponseDTO getStudentResponseDTO = new GetStudentResponseDTO();
            getStudentResponseDTO.setCity((String) x.get("CITY"));
            getStudentResponseDTO.setName(x.get("NAME", String.class));
            getStudentResponseDTO.setAge(x.get(2, Integer.class));
            return getStudentResponseDTO;
        }).toList();
        logger.debug("Total {} size of students found with {} country", myResult.size(), countryName);

        return myResult;
    }

    @Transactional
    public Integer updateStudentAgeToThirtyFromThisCity(String city) {
        logger.debug("Updating All student's age with cityName {}", city);
        int i = 0;
        try {
            i = myStudentRepository.updateAgeByTwoForStudentsFromThisCity(city);
            logger.debug("Total {} numbers of students were updated", i);
        } catch (Exception e) {
            logger.error("Error occurred while Updating the Student's age with CityName {}", city);
            throw new RuntimeException("Error occurred while Updating the Student: " + e.getMessage());
        }
        return i;
    }

    @Transactional
    public Integer deleteStudentByName(String name) {
        logger.debug("Deleting student with name {}", name);
        int i = 0;
        try {
            i = myStudentRepository.deleteStudentByStudentName(name);
            logger.debug("Total {} numbers of students were deleted", i);
        } catch (Exception e) {
            logger.error("Error occurred while deleting the Student with name {}", name);
            throw new RuntimeException("Error occurred while deleting the Student with message: " + e.getMessage());
        }
        return i;
    }

    @Transactional
    public List<GetStudentResponseDTO> fetchStudentByNameLength(String city) {
        logger.info("Used Tuple here");
        List<Tuple> tuples = myStudentRepository.fetchStudentWithCityBasedOnNameLengthFromCity(city);
        List<GetStudentResponseDTO> list = tuples.stream().map(x -> {
            GetStudentResponseDTO getStudentResponseDTO = new GetStudentResponseDTO();
            getStudentResponseDTO.setCity(x.get("CITY", String.class));
            getStudentResponseDTO.setName(x.get("NAME", String.class));
            getStudentResponseDTO.setAge(x.get("AGE", Integer.class));
            return getStudentResponseDTO;

        }).toList();
        logger.debug("total {} students are fetched", list.size());
        return list;
    }

    @Transactional
    public List<CityStudentCountDTO> getCityWithStudentCountMoreThanTwo() {

        List<Tuple> cityWithStudentCountMoreThanTwo = myStudentRepository.getCityWithStudentCountMoreThanTwo();
        List<CityStudentCountDTO> list = cityWithStudentCountMoreThanTwo.stream().map(x -> {
            CityStudentCountDTO cityStudentCountDTO = new CityStudentCountDTO();
            cityStudentCountDTO.setCity(x.get("CITY", String.class));
            cityStudentCountDTO.setStudentCount(x.get("Student_count", Long.class));

            return cityStudentCountDTO;

        }).toList();
        logger.debug("total {} city are fetched having more than 2 students using Criteria API", list.size());
        return list;

    }

    @Transactional
    public List<GetStudentResponseDTO> fetchStudentWithCityByStudentNameLength(String city) {
        List<Tuple> tuples = myStudentRepository.fetchStudentWithCityOrderByNameLength(city);
        List<GetStudentResponseDTO> list = tuples.stream().map(x -> {
            GetStudentResponseDTO getStudentResponseDTO = new GetStudentResponseDTO();
            getStudentResponseDTO.setName(x.get("NAME", String.class));
            getStudentResponseDTO.setCity(x.get("CITY", String.class));
            return getStudentResponseDTO;
        }).toList();
        logger.debug("total {} students are fetched with city details", list.size());
        return list;
    }

    @Transactional
    public List<CityStudentCountDTO> getStudentWithMoreThanOneCount() {
        List<Tuple> tuples = myStudentRepository.FetchCityWithStudentCountMoreThanOne();
        List<CityStudentCountDTO> list = tuples.stream().map(x -> {
            CityStudentCountDTO cityStudentCountDTO = new CityStudentCountDTO();
            cityStudentCountDTO.setStudentCount(x.get("STUDENT_COUNT", Long.class));
            cityStudentCountDTO.setCity(x.get("CITY", String.class));
            return cityStudentCountDTO;
        }).toList();
        logger.debug("total {} cities are fetched using JPQL", list.size());
        return list;
    }
}
