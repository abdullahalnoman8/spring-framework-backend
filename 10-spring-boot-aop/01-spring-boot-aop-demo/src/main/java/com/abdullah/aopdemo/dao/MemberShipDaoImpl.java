package com.abdullah.aopdemo.dao;

import org.springframework.stereotype.Repository;

@Repository
public class MemberShipDaoImpl implements MemberShipDao{
    @Override
    public void addAccount() {
        System.out.println(getClass() + " : DOING MY DB WORK: ADDING a Membership ACCOUNT");
    }

    @Override
    public boolean addSillyMember() {
        System.out.println(getClass() + " : DOING MY DB WORK: ADDING a Silly Member from Membership class");
        return false;
    }
}
