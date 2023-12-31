package com.abdullah.cruddemo.dao;

import com.abdullah.cruddemo.entity.Instructor;
import com.abdullah.cruddemo.entity.InstructorDetail;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class AppDaoImpl implements AppDao {

    // define field for entity manager
    private final EntityManager entityManager;

    // inject entity manager using constructor injection

    @Autowired
    public AppDaoImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    @Transactional
    public void save(Instructor theInstructor) {
        entityManager.persist(theInstructor);
    }

    @Override
    public Instructor findInstructorById(int theId) {
        return entityManager.find(Instructor.class, theId);
    }

    @Override
    @Transactional
    public void deleteInstructorById(int theId) {
        // retrieve the instructor
        Instructor tempInstructor = entityManager.find(Instructor.class, theId);

        // delete the instructor
        entityManager.remove(tempInstructor);
    }

    @Override
    public InstructorDetail findInstructorDetailById(int theId) {
        return entityManager.find(InstructorDetail.class,theId);
    }

    @Override
    @Transactional
    public void deleteInstructorDetailById(int theId) {
        // retrieve the instructor details first
        InstructorDetail instructorDetail = entityManager.find(InstructorDetail.class, theId);
        // remove the associated object reference
        // break bi-directional link
        instructorDetail.getInstructor().setInstructorDetail(null);
        // delete the instructor detail
        entityManager.remove(instructorDetail);
    }
}
