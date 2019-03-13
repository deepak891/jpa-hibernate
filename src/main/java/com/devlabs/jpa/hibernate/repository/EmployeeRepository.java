package com.devlabs.jpa.hibernate.repository;

import com.devlabs.jpa.hibernate.entity.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
@Transactional
public class EmployeeRepository {

    private Logger log = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private EntityManager entityManager;

    //insert an employee
    public void insert(Employee employee) {
        entityManager.persist(employee);
    }

    //retrieve all employee

    public List<FullTimeEmployee> retrieveAllFullEmployee(){
        return entityManager
                .createQuery("select e from FullTimeEmployee e", FullTimeEmployee.class)
                .getResultList();
    }

    public List<PartTimeEmployee> retrieveAllPartEmployee(){
        return entityManager
                .createQuery("select e from PartTimeEmployee e", PartTimeEmployee.class)
                .getResultList();
    }



}
