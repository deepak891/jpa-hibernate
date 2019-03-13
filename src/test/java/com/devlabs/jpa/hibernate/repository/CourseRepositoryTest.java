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
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

import static org.assertj.core.api.Assertions.*;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = DevlabsJpaHibernateApplication.class)
public class CourseRepositoryTest {

    private Logger log = LoggerFactory.getLogger(this.getClass());

    @Autowired
    CourseRepository courseRepository;

    @Autowired
    EntityManager em;

    @Before
    public void setUp() throws Exception {
    }

    @Test
    public void findById() {
        Course course = courseRepository.findById(10001L);
        assertThat(course.getName())
                .isNotEmpty()
                .isNotEqualTo("Jpa in 100 steps")
                .isEqualTo("Jpa in 50 steps");

    }

    @Test
    @Transactional
    public void findByIdFirstLevelCacheDemo() {
        Course course = courseRepository.findById(10001L);
        log.info("First Course retrieved => {} ", course);

        Course course1 = courseRepository.findById(10001L);
        log.info("First Course retrieved again=> {} ", course1);

        assertThat(course.getName()).isNotEqualTo("Jpa in 100 steps");
        assertThat(course1.getName()).isNotEqualTo("Jpa in 100 steps");
    }

    @Test
    @DirtiesContext
    public void deleteById() {
        assertThat(courseRepository.findById(10002L).getName())
                .isNotNull()
                .isEqualTo("Springbot in 100 steps");

        courseRepository.deleteById(10002L);

        assertThat(courseRepository.findById(10002L))
                .isNull();
    }

    @Test
    @DirtiesContext
    public void saveNewCourse() {
        Course course = new Course("Docker For Beginners");
        courseRepository.save(course);

        assertThat(courseRepository.findById(1l).getName()).isNotEmpty()
                .isEqualTo("Docker For Beginners");
    }

    @Test
    @DirtiesContext
    public void saveExistingCourse() {
        Course course = courseRepository.findById(10002L);
        course.setName("Spring MVC for Beginners");
        courseRepository.save(course);

        assertThat(courseRepository.findById(10002L).getName()).isNotEmpty()
                .isEqualTo("Spring MVC for Beginners");
    }

    @Test
    public void playWithEntityManager() {
        courseRepository.playWithEntityManager();
    }

    @Test
    @Transactional
    public void retrieveReviewsForCourse() {
        Course course = courseRepository.findById(10002L);
        log.info("{}", course.getReviews());
    }

    @Test
    @Transactional
    public void retrieveCourseForReviews() {
        Review review = em.find(Review.class, 50001L);
        log.info("{}", review.getCourse());
    }
}