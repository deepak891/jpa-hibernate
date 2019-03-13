package com.devlabs.jpa.hibernate.repository;

import com.devlabs.jpa.hibernate.entity.Course;
import com.devlabs.jpa.hibernate.entity.Passport;
import com.devlabs.jpa.hibernate.entity.Student;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

@Repository
@Transactional
public class StudentRepository {

    private Logger log = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private EntityManager em;

    public Student findById(Long id){
        return em.find(Student.class, id);
    }

    public Student save(Student student){
        if(student.getId() == null) {
            em.persist(student);
        }else {
            em.merge(student);
        }
        return student;
    }

    public void deleteById(Long id){
        Student student = findById(id);
        em.remove(student);
    }

    public void saveStudentWithPassport() {
        Passport passport = new Passport("Z12324234");
        em.persist(passport);

        Student student = new Student("Duke");

        student.setPassport(passport);
        em.persist(student);
    }

    public void insertHardCodedStudentAndCourse() {
        Student student = new Student("Mark");
        Course course = new Course("Python in 100 steps");

        em.persist(student);
        em.persist(course);

        student.addCourse(course);
        course.addStudent(student);

        em.persist(student);
    }

    public void insertStudentAndCourse(Student student, Course course) {
        student.addCourse(course);
        course.addStudent(student);

        em.persist(student);
        em.persist(course);
    }

}
