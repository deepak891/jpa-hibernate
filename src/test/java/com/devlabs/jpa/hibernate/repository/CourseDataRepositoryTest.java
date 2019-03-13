package com.devlabs.jpa.hibernate.repository;

import com.devlabs.jpa.hibernate.DevlabsJpaHibernateApplication;
import com.devlabs.jpa.hibernate.entity.Course;
import com.devlabs.jpa.hibernate.entity.Review;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = DevlabsJpaHibernateApplication.class)
public class CourseDataRepositoryTest {

    private Logger log = LoggerFactory.getLogger(this.getClass());

    @Autowired
    CourseDataRepository courseRepository;

    @Test
    public void findById_course_present(){
        Optional<Course> courseOptional = courseRepository.findById(10001L);
        assertThat(courseOptional).isPresent();
    }

    @Test
    public void findById_course_not_present(){
        Optional<Course> courseOptional = courseRepository.findById(100L);
        assertThat(courseOptional).isEmpty();
    }

    @Test
    public void playingAroundWithSpringDataRepository(){
        Course course = new Course("Microservices in 100 steps");
        courseRepository.save(course);

        course.setName("Microservices in 100 steps - updated");
        courseRepository.save(course);


        log.info("Courses {} =>" , courseRepository.findAll());
        //Courses [Course{name='Microservices in 100 steps - updated'}, Course{name='Jpa in 50 steps'}, Course{name='Springbot in 100 steps'}, Course{name='Microservice in 100 steps'}] =>
        log.info("Courses count {} =>" , courseRepository.count());
    }

    @Test
    public void sort(){
        Sort sort = new Sort(Sort.Direction.DESC,"name");

        log.info("Courses {} =>" , courseRepository.findAll(sort));
        //Courses [Course{name='Microservices in 100 steps - updated'}, Course{name='Jpa in 50 steps'}, Course{name='Springbot in 100 steps'}, Course{name='Microservice in 100 steps'}] =>
        log.info("Courses count {} =>" , courseRepository.count());
    }

    @Test
    public void pagination(){
        PageRequest pageRequest = PageRequest.of(0,3);

        Page<Course> firstPage = courseRepository.findAll(pageRequest);
        log.info("Page {} =>" , firstPage.getContent());

        Pageable secondPageable = firstPage.nextPageable();
        Page<Course> secondPage = courseRepository.findAll(secondPageable);
        log.info("Page 2 {} =>" , secondPage.getContent());
    }

    @Test
    public void findByName(){
        Course course = courseRepository.findByName("Springbot in 100 steps");
        log.info("Courses Name {} =>" , course);
    }
}