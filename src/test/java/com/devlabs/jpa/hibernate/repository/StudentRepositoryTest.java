package com.devlabs.jpa.hibernate.repository;

import com.devlabs.jpa.hibernate.DevlabsJpaHibernateApplication;
import com.devlabs.jpa.hibernate.entity.Passport;
import com.devlabs.jpa.hibernate.entity.Student;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = DevlabsJpaHibernateApplication.class)
public class StudentRepositoryTest {

    private Logger log = LoggerFactory.getLogger(this.getClass());

    @Autowired
    StudentRepository repository;

    @Autowired
    EntityManager em;

    @Test
    @Transactional
    public void retrieveStudentAndPassportDetail() {
        Student student = em.find(Student.class, 20001L);
        log.info("Student -> {} " , student);
        log.info("Passport -> {} " , student.getPassport());

    }

    @Test
    @Transactional
    public void retrievePassportAndAssociatedStudent() {
        Passport passport = em.find(Passport.class, 40001L);
        log.info("passport -> {} " , passport);
        log.info("Student -> {} " , passport.getStudent());

    }

    @Test
    @Transactional
    public void retrieveStudentAndAssociatedCourses() {
        Student student = em.find(Student.class, 20001L);
        log.info("Student -> {} " , student);
        log.info("Courses -> {} " , student.getCourses());

    }

    @Test
    @Transactional
    public void someTest(){
        //Database operation1 - retrieve student
        Student student = em.find(Student.class, 20001L);
        //Persistence Context(student)

        //Database operation2 - retrieve passport
        Passport passport = student.getPassport();
        //Persistence Context(student, passport)
        //Database operation3 - update passport
        passport.setNumber("E111111");
        //Persistence Context(student, passport++)
        //Database operation4 - update student
        student.setName("Deepak Bhagat");
        //Persistence Context(student++, passport++)
    }

    @Test
    public void save() {
    }

    @Test
    public void deleteById() {
    }

    @Test
    public void saveStudentWithPassport() {
    }
}