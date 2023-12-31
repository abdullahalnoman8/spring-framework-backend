package com.abdullah.cruddemo.dao;

import com.abdullah.cruddemo.entity.Course;
import com.abdullah.cruddemo.entity.Instructor;
import com.abdullah.cruddemo.entity.InstructorDetail;
import com.abdullah.cruddemo.entity.Student;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

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

        // get the courses
        List<Course> courses = tempInstructor.getCourses();

        // break association of all courses for the instructor
        for (Course theCourse: courses) {
             theCourse.setInstructor(null);
        }

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

    @Override
    public List<Course> findCoursesByInstructorId(int theId) {
        // create a query
        TypedQuery<Course> query = entityManager.createQuery("from Course where instructor.id =:data", Course.class);
        query.setParameter("data", theId);
        // execute query
        List<Course> courses = query.getResultList();
        return courses;
    }

    @Override
    public Instructor findInstructorByIdJoinFetch(int theId) {
        // create query
       /* TypedQuery<Instructor> query = entityManager.createQuery("Select i from Instructor i " +
                                                                    "JOIN FETCH i.courses " +
                                                                    "WHERE i.id =:data", Instructor.class);*/
        TypedQuery<Instructor> query = entityManager.createQuery("Select i from Instructor i " +
                                                                    "JOIN FETCH i.courses " +
                                                                    "JOIN FETCH i.instructorDetail " +
                                                                    "WHERE i.id =:data", Instructor.class);
        query.setParameter("data", theId);
        // execute query
        Instructor instructor = query.getSingleResult();
        return instructor;
    }

    @Override
    @Transactional
    public void updateInstructor(Instructor theTempInstructor) {
        entityManager.merge(theTempInstructor);
    }

    @Override
    @Transactional
    public void updateCourse(Course theCourse) {
         entityManager.merge(theCourse);
    }

    @Override
    public Course findCourseById(int theId) {
        return entityManager.find(Course.class, theId);
    }

    @Override
    @Transactional
    public void deleteCourseById(int theId) {
        Course course = entityManager.find(Course.class, theId);
        entityManager.remove(course);
    }

    @Override
    @Transactional
    public void save(Course theCourse) {
        entityManager.persist(theCourse);
    }

    @Override
    public Course findCourseAndReviewsByCourseId(int theId) {
        // create a query
        TypedQuery<Course> query = entityManager.createQuery(
                             "SELECT c from Course " +
                                "c JOIN FETCH c.reviews " +
                                "WHERE c.id =:data", Course.class);
        query.setParameter("data", theId);
        // execute a query
        Course course = query.getSingleResult();
        return course;
    }

    @Override
    public Course findCourseAndStudentsByCourseId(int theId) {
        // create a query
        TypedQuery<Course> query = entityManager.createQuery(
                                                            "select c from Course " +
                                                               "c JOIN FETCH c.students " +
                                                               "WHERE c.id =:data", Course.class);
        // execute a query

        query.setParameter("data", theId);

        // return result
        Course course = query.getSingleResult();
        return course;
    }

    @Override
    public Student findStudentAndCoursesByStudentId(int theId) {
        // write a query
        TypedQuery<Student> query = entityManager.createQuery(
                                                         "select s from Student " +
                                                            "s JOIN FETCH s.courses " +
                                                            "where s.id=:data", Student.class);
        // execute a query
        query.setParameter("data", theId);

        // return the result
        Student student = query.getSingleResult();
        return student;
    }

    @Override
    @Transactional
    public void update(Student tempStudent) {
       entityManager.merge(tempStudent);
    }

    @Override
    @Transactional
    public void deleteStudentById(int theId) {
        // retrieve the student
        Student tempStudent = entityManager.find(Student.class, theId);
        // delete the student
        entityManager.remove(tempStudent);
    }
}
