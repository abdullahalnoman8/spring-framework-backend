package com.abdullah.aopdemo.dao;

import org.springframework.stereotype.Repository;

@Repository
public class AccountDaoImpl implements AccountDao{
    @Override
    public void addAccount() {
        System.out.println(getClass() + " : DOING MY DB WORK: ADDING AN ACCOUNT");
    }
}
