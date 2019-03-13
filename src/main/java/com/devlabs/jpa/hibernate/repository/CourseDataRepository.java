package com.devlabs.jpa.hibernate.repository;

import com.devlabs.jpa.hibernate.entity.Course;
import com.devlabs.jpa.hibernate.entity.Review;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.webmvc.RepositoryRestController;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.List;

@RepositoryRestResource(path = "/courses")
public interface CourseDataRepository  extends JpaRepository<Course, Long> {
    Course findByName(String name);
    Course findByNameAndId(String name, Long id);
    Course findByNameOrderById(String name);

    @Query("Select c From Course c where name like '%100 Step%'")
    List<Course> courseWith100Steps();

    @Query(value = "Select c From Course c where name like '%100 Step%'", nativeQuery = true)
    List<Course> courseWith100StepsUsingNativeQuery();

    @Query(name = "query_get_100_Step_courses")
    List<Course> courseWith100StepsUsingNamedQuery();
}
