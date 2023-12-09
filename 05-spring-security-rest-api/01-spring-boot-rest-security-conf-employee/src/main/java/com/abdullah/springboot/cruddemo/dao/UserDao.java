package com.abdullah.springboot.cruddemo.dao;


import com.abdullah.springboot.cruddemo.entity.User;

public interface UserDao {

    User findByUserName(String userName);
    
}
