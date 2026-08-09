package com.lisa.myspringbootwithjpa.repository;

import com.lisa.myspringbootwithjpa.model.Student;
import com.lisa.myspringbootwithjpa.repository.custom.MyCustomRepository;
import jakarta.persistence.LockModeType;
import jakarta.persistence.Tuple;
import org.springframework.data.domain.Limit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface MyStudentRepository extends JpaRepository<Student, String>, MyCustomRepository {

    @Transactional(timeout = 10)
    public List<Student> findByName(String name);
    public Student findByRollId(String rollId);
    public List<Student> findNRIStudentsFromGivenState(String stateName);

    @Query("select s from Student s where s.name = :theName or s.rollId= :theRollId " )
    public Student findByNameOrRollId(@Param("theName") String theName,@Param("theRollId") String theRollId);

    @Query("select s from Student s where s.name LIKE %?#{escape[0]}% escape ?#{escapeCharacter()} " )
    public Student searchStudentWithAdvanceNameSearchWithSpecialCharacterSupport(String name, Limit limit);


    @Modifying(clearAutomatically = true, flushAutomatically = true)
    @Query("update Student s set s.bioData.nriStatus= ?2 where s.rollId = ?1")
    @Lock(LockModeType.PESSIMISTIC_FORCE_INCREMENT)
    public int updateTheNRIStatusOFAnyStudent(@Param("roll") String rollId,@Param("nriStatusUpdate") Boolean status);

    @Query("SELECT s FROM Student s JOIN FETCH s.address a WHERE a.city= ?1 AND a.state= ?2")//Using FETCH to avoid N+1 problem
    public List<Student> getStudentsFromThisState(String city, String state);

    @Query("SELECT s FROM Student s JOIN FETCH address a WHERE a.city= ?1 AND a.state= ?2")//Here i used address , not s.address , both are same
    public List<Student> getStudentsFromThisStatex(String city, String state);


    @Query("SELECT s FROM Student s LEFT JOIN FETCH address a WHERE  a.city= ?1 AND a.state= ?2")
    public List<Student> getStudentsFromThisStateLeftOuterJoin(String city, String state);

    @Query("SELECT s FROM Student s LEFT JOIN FETCH  s.address a WHERE   a.city IN :cities")
    public List<Student> getStudentsFromTheseCities(List<String> cities);

    @Query("SELECT s FROM Student s LEFT JOIN  address a ON  a.city= ?1 AND a.state= ?2")
    public List<Student> getStudentsFromThisStateLeftOuterJoinNoFetch(String city, String state);//Here No FETCH is used, So ON can be used . But it will give N+1 issue
    @Query("""
        SELECT 
            s.name as NAME,
            a.city as CITY
        FROM Student s
        JOIN s.address a
        WHERE a.city = :city
        ORDER BY LENGTH(s.name) DESC
        """)
    public List<Tuple> fetchStudentWithCityOrderByNameLength(String city);
    @Query("""
            SELECT COUNT(s) AS STUDENT_COUNT,
            a.city AS CITY
        FROM Student s
        JOIN s.address a
        GROUP BY a.city
        HAVING COUNT(s) >1
        """)
    public List<Tuple> FetchCityWithStudentCountMoreThanOne();
}
