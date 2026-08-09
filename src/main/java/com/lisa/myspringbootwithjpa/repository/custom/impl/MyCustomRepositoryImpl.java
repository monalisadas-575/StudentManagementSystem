package com.lisa.myspringbootwithjpa.repository.custom.impl;

import com.lisa.myspringbootwithjpa.model.*;
import com.lisa.myspringbootwithjpa.model.*;
import com.lisa.myspringbootwithjpa.repository.custom.MyCustomRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Tuple;
import jakarta.persistence.criteria.*;
import org.springframework.stereotype.Component;

import java.util.List;
@Component
public class MyCustomRepositoryImpl implements MyCustomRepository {

    @PersistenceContext
    EntityManager entityManager;

    @Override
    public List<Student> giveMeStudentsFromName(String name) {
        CriteriaBuilder criteriaBuilder= entityManager.getCriteriaBuilder();

        CriteriaQuery<Student> criteriaQuery= criteriaBuilder.createQuery(Student.class);
        Root<Student> root= criteriaQuery.from(Student.class);

        criteriaQuery
                //.select(root.get(Student_.ID))
                .where(criteriaBuilder.equal(root.get(Student_.NAME),name));

        List<Student> studentList= entityManager.createQuery(criteriaQuery).getResultList();
        return studentList;
    }

    @Override
    public List<Student> giveMeStudentsWithNameAndCity(String name, String city) {
        CriteriaBuilder criteriaBuilder= entityManager.getCriteriaBuilder();

        CriteriaQuery<Student> criteriaQuery= criteriaBuilder.createQuery(Student.class);
        Root<Student> root= criteriaQuery.from(Student.class);
        Join<Student, Address> join= root.join(Student_.ADDRESS);

        criteriaQuery
                .where(criteriaBuilder.and(
                        criteriaBuilder.equal(root.get(Student_.NAME),name)
                        ,criteriaBuilder.equal(join.get(Address_.city),city)
                ));

        List<Student> studentList= entityManager.createQuery(criteriaQuery).getResultList();
        return studentList;
    }

    @Override
    public List<String> fetchCountryForStudentsWithGivenCourseName(String name) {
        CriteriaBuilder builder= entityManager.getCriteriaBuilder();

        CriteriaQuery<String> criteriaQuery= builder.createQuery(String.class);
        Root<Address> addressRoot= criteriaQuery.from(Address.class);
        Join<Address,Student> studentJoin= addressRoot.join(Address_.THE_STUDENT);

        Subquery<Long> subquery= criteriaQuery.subquery(Long.class);
        Root<Student> root= subquery.from(Student.class);
        Join<Student, TutorialCourse> join= root.join(Student_.TUTORIAL_COURSES);
        subquery.
                select(root.get(Student_.ID)).
                where(builder.equal(join.get(TutorialCourse_.COURSE_NAME),name));

        criteriaQuery
                .select(addressRoot.get(Address_.COUNTRY))
                .where(studentJoin.get(Student_.ID).in(subquery));

        List<String> countries= entityManager.createQuery(criteriaQuery).getResultStream().toList();
        return countries;
    }

    @Override
    public List<Tuple> fetchStudentWithTheirCity(String country) {
        CriteriaBuilder criteriaBuilder= entityManager.getCriteriaBuilder();

        CriteriaQuery<Tuple> criteriaQuery= criteriaBuilder.createQuery(Tuple.class);
        Root<Student> root= criteriaQuery.from(Student.class);
        Join<Student,Address> join= root.join(Student_.ADDRESS);

        criteriaQuery
                .multiselect(
                        root.get(Student_.NAME).alias("NAME"),
                        join.get(Address_.CITY).alias("CITY"),
                        root.get(Student_.AGE).alias("AGE"))
                .where(criteriaBuilder.equal(join.get(Address_.COUNTRY),country));

        List<Tuple> results= entityManager.createQuery(criteriaQuery).getResultList();

        return results;
    }

    @Override
    public Integer updateAgeByTwoForStudentsFromThisCity(String city) {
        CriteriaBuilder criteriaBuilder= entityManager.getCriteriaBuilder();
        CriteriaUpdate<Student> criteriaUpdate= criteriaBuilder.createCriteriaUpdate(Student.class);
        Root<Student> root= criteriaUpdate.from(Student.class);
        Join<Student,Address> join= root.join(Student_.ADDRESS);

        criteriaUpdate
                .set(root.get(Student_.AGE),30)
                .where(criteriaBuilder.equal(join.get(Address_.CITY),city));

        Integer count=entityManager.createQuery(criteriaUpdate).executeUpdate();

        return count;
    }

    @Override
    public Integer deleteStudentByStudentName(String name) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaDelete<Student> criteriaQuery = criteriaBuilder.createCriteriaDelete(Student.class);
        Root<Student> root = criteriaQuery.from(Student.class);
        criteriaQuery.
                where(criteriaBuilder.equal(root.get(Student_.NAME),name));
        int count = entityManager.createQuery(criteriaQuery).executeUpdate();
        return count;
    }

    @Override
    public List<Tuple> fetchStudentWithCityBasedOnNameLengthFromCity(String city) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Tuple> criteriaQuery = criteriaBuilder.createQuery(Tuple.class);
        Root<Student> root = criteriaQuery.from(Student.class);
        Join<Student,Address> join = root.join(Student_.ADDRESS);

        criteriaQuery.
                multiselect(
                        root.get(Student_.NAME).alias("NAME"),
                        join.get(Address_.CITY).alias("CITY"))
                .where(criteriaBuilder.equal(join.get(Address_.CITY),city)).
                orderBy(
                        criteriaBuilder.desc(
                                criteriaBuilder.length(root.get(Student_.NAME))
                        )
                );
        List<Tuple> list = entityManager.createQuery(criteriaQuery).getResultStream().toList();

        return list;
    }

    @Override
    public List<Tuple> getCityWithStudentCountMoreThanTwo() {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Tuple> criteriaQuery = criteriaBuilder.createQuery(Tuple.class);
        Root<Student> root = criteriaQuery.from(Student.class);
        Join<Student,Address> join = root.join(Student_.ADDRESS);
        Expression<Long> count = criteriaBuilder.count(root);

        criteriaQuery.
                multiselect(
                        count.alias("Student_count"),
                        join.get(Address_.CITY).alias("CITY")

                );
        criteriaQuery.groupBy(join.get(Address_.CITY));
        criteriaQuery.having(
                criteriaBuilder.greaterThan(count,1l)
        );
        return entityManager.createQuery(criteriaQuery).getResultList();

    }


}
