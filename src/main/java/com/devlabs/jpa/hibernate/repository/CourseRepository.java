package com.devlabs.jpa.hibernate.repository;

import com.devlabs.jpa.hibernate.entity.Course;
import com.devlabs.jpa.hibernate.entity.Review;
import com.devlabs.jpa.hibernate.entity.ReviewRating;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
@Transactional
public class CourseRepository {

    private Logger log = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private EntityManager entityManager;

    public Course findById(Long id){
        return entityManager.find(Course.class, id);
    }

    public Course save(Course course){
        if(course.getId() == null) {
            entityManager.persist(course);
        }else {
            entityManager.merge(course);
        }
        return course;
    }

    public void deleteById(Long id){
        Course course = findById(id);
        entityManager.remove(course);
    }

    public void playWithEntityManager() {
        log.info("playWithEntityManager - start");

        Course course1 = new Course("Docker For Beginners");
        entityManager.persist(course1);
        Course course2 = new Course("Angular in 100 Steps");
        entityManager.persist(course2);

        entityManager.flush();

        course1.setName("Docker For Beginners Updated");
        course2.setName("Angular in 100 Steps updated");

        entityManager.refresh(course1);

        log.info(course1.getName());
        log.info(course2.getName());

        entityManager.flush();
    }

    public void addHardCodedReviewForCourse() {
        //Get the course 10003
        Course course = findById(10003L);
        log.info("course.getReviews {}", course.getReviews());

        //add 2 review to the course
        Review review1 = new Review("Greate hands-on stuff", ReviewRating.FIVE);
        Review review2 = new Review("hands-on stuff", ReviewRating.FIVE);

        course.addReview(review1);
        review1.setCourse(course);
        course.addReview(review2);
        review2.setCourse(course);
        //save it to the database
        entityManager.persist(review1);
        entityManager.persist(review2);
    }

    public void addReviewForCourse(Long courseId, List<Review> reviews) {
        Course course = findById(courseId);
        log.info("course.getReviews {}", course.getReviews());

        reviews.stream().forEach( review -> {
            course.addReview(review);
            review.setCourse(course);
            entityManager.persist(review);
        });

    }

}
