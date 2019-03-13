package com.devlabs.jpa.hibernate.repository;

import com.devlabs.jpa.hibernate.DevlabsJpaHibernateApplication;
import com.devlabs.jpa.hibernate.entity.Course;
import com.devlabs.jpa.hibernate.entity.Student;
import org.assertj.core.api.Assertions;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = DevlabsJpaHibernateApplication.class)
public class JPQLTest {

    private Logger log = LoggerFactory.getLogger(this.getClass());

    @Autowired
    EntityManager em;

    @Before
    public void setUp() throws Exception {
    }

    @Test
    public void jpqlTest() {
        List resultList = em.createQuery("query_get_all_courses").getResultList();
        log.info("Select c From Course c {}", resultList);
    }

    @Test
    public void jpqlTyped() {
        TypedQuery<Course> query = em.createNamedQuery("query_get_all_courses", Course.class);
        List<Course> courses = query.getResultList();
    }

    @Test
    public void jpql_where() {
        TypedQuery<Course> query = em.createNamedQuery("query_get_100_Step_courses", Course.class);
        List<Course> courses = query.getResultList();
        log.info("Select c From Course c where name like '%100 Step% {}", courses);
    }

    @Test
    public void jpql_courses_without_student() {
        TypedQuery<Course> query = em.createQuery("Select c from Course c where c.students is empty",
                Course.class);
        List<Course> resultList =  query.getResultList();
        log.info("Result -> {}", resultList);
    }

    @Test
    public void jpql_courses_without_atleast_two_student() {
        TypedQuery<Course> query = em.createQuery("Select c from Course c where size(c.students) >= 2",
                Course.class);
        List<Course> resultList =  query.getResultList();
        log.info("Result -> {}", resultList);
    }

    @Test
    public void jpql_courses_ordered_by_students() {
        TypedQuery<Course> query = em.createQuery("Select c from Course c order by size(c.students)",
                Course.class);
        List<Course> resultList =  query.getResultList();
        log.info("Result -> {}", resultList);
    }

    @Test
    public void jpql_students_with_passport_in_a_certain_pattern() {
        TypedQuery<Student> query = em.createQuery("Select s from Student where s.passport.number like '%123%'",
                Student.class);
        List<Student> resultList =  query.getResultList();
        log.info("Result -> {}", resultList);
    }

    //like
    //BETWEEN 100 AND 1000
    //IS NULL
    //upper, lower, trim, length

    //JOIN => Select c, s from Course c JOIN c.students s
    //LEFT JOIN => Select c, s from Course c LEFT JOIN c.students s
    //RIGHT JOIN => Select c, s from Course c, Student s

    @Test
    public void join(){
        Query query = em.createQuery("Select c, s from Course c JOIN c.students s");
        List<Object[]> resultList =  query.getResultList();

        log.info("Result size-> {}", resultList.size());
        for(Object[] result: resultList) {
            log.info("Course {} : Student {}", result[0],result[1]);
        }


    }

}