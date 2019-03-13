package com.devlabs.jpa.hibernate.repository;

import com.devlabs.jpa.hibernate.DevlabsJpaHibernateApplication;
import com.devlabs.jpa.hibernate.entity.Course;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = DevlabsJpaHibernateApplication.class)
public class NativeQueriesTest {

    private Logger log = LoggerFactory.getLogger(this.getClass());

    @Autowired
    EntityManager em;

    @Before
    public void setUp() throws Exception {
    }

    @Test
    public void native_query_basic() {
        List resultList = em.createNativeQuery("SELECT * FROM COURSE", Course.class).getResultList();
        log.info("SELECT * FROM COURSE {}", resultList);
    }

    @Test
    public void native_query_with_paramter() {
        Query query = em.createNativeQuery("SELECT * FROM COURSE WHERE ID = ?", Course.class);
        query.setParameter(1, 1);
        List resultList = query.getResultList();
        log.info("SELECT * FROM COURSE {}", resultList);
    }

    @Test
    public void native_query_with_named_paramter() {
        Query query = em.createNativeQuery("SELECT * FROM COURSE WHERE ID = :id", Course.class);
        query.setParameter("id", 1);
        List resultList = query.getResultList();
        log.info("SELECT * FROM COURSE {}", resultList);
    }

    @Test
    @Transactional
    public void native_query_to_mass_update() {
        Query query = em.createNativeQuery("UPDATE COURSE SET LAST_UPDATED_DATE=SYSDATE()");
        int count = query.executeUpdate();
        log.info("noofUpdatedRows: count {}", count);
    }

}