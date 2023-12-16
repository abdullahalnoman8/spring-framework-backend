package com.abdullah.aopdemo.dao;

import com.abdullah.aopdemo.Account;
import org.springframework.stereotype.Repository;

@Repository
public class AccountDaoImpl implements AccountDao{
    @Override
    public void addAccount(Account theAccount) {
        System.out.println(getClass() + " : DOING MY DB WORK: ADDING AN ACCOUNT");
    }
}
