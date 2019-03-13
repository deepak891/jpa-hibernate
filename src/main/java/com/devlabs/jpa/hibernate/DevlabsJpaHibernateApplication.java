package com.devlabs.jpa.hibernate;

import com.devlabs.jpa.hibernate.entity.Course;
import com.devlabs.jpa.hibernate.entity.FullTimeEmployee;
import com.devlabs.jpa.hibernate.entity.PartTimeEmployee;
import com.devlabs.jpa.hibernate.entity.Student;
import com.devlabs.jpa.hibernate.repository.CourseRepository;
import com.devlabs.jpa.hibernate.repository.EmployeeRepository;
import com.devlabs.jpa.hibernate.repository.StudentRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.math.BigDecimal;

@SpringBootApplication
public class DevlabsJpaHibernateApplication implements CommandLineRunner {

	private Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private CourseRepository courseRepository;

	@Autowired
	private StudentRepository studentRepository;

	@Autowired
	private EmployeeRepository employeeRepository;


	public static void main(String[] args) {
		SpringApplication.run(DevlabsJpaHibernateApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		//courseRepository.playWithEntityManager();
		//studentRepository.saveStudentWithPassport();
		//courseRepository.addHardCodedReviewForCourse();
		//List<Review> reviews = new ArrayList<>();
		//reviews.add(new Review("Good Stuff", "5"));
		//reviews.add(new Review("Good hands on stuff", "5"));
		//courseRepository.addReviewForCourse(10003L, reviews);

		//studentRepository.insertStudentAndCourse(new Student("Mark"), new Course("Python in 100 Steps"));

//		employeeRepository.insert( new PartTimeEmployee("Jill", new BigDecimal("50")));
//		employeeRepository.insert( new FullTimeEmployee("Jill", new BigDecimal("10000")));
//
//		logger.info("All retrieveAllFullEmployee -{}" , employeeRepository.retrieveAllFullEmployee());
//		logger.info("All retrieveAllPartEmployee -{}" , employeeRepository.retrieveAllPartEmployee());

	}

}
