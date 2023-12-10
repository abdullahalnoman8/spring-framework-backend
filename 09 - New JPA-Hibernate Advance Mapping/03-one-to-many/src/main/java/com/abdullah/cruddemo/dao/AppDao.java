package com.abdullah.cruddemo.dao;

import com.abdullah.cruddemo.entity.Instructor;
import com.abdullah.cruddemo.entity.InstructorDetail;

public interface AppDao {
    void save(Instructor theInstructor);

    Instructor findInstructorById(int theId);

    void deleteInstructorById(int theId);

    InstructorDetail findInstructorDetailById(int theId);

    void deleteInstructorDetailById(int theId);
}
