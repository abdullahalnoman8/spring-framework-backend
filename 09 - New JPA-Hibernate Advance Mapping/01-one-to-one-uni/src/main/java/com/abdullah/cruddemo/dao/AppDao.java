package com.abdullah.cruddemo.dao;

import com.abdullah.cruddemo.entity.Instructor;

public interface AppDao {
    void save(Instructor theInstructor);

    Instructor findInstructorById(int theId);

}
