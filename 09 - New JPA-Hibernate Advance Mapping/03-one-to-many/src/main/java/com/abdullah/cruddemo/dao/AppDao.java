package com.abdullah.cruddemo.dao;

import com.abdullah.cruddemo.entity.Course;
import com.abdullah.cruddemo.entity.Instructor;
import com.abdullah.cruddemo.entity.InstructorDetail;

import java.util.List;

public interface AppDao {
    void save(Instructor theInstructor);

    Instructor findInstructorById(int theId);

    void deleteInstructorById(int theId);

    InstructorDetail findInstructorDetailById(int theId);

    void deleteInstructorDetailById(int theId);

    List<Course> findCoursesByInstructorId(int theId);

    Instructor findInstructorByIdJoinFetch(int theId);

    void updateInstructor(Instructor theTempInstructor);
}
