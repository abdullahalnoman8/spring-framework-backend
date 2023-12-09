package com.abdullah.springboot.cruddemo.dao;


import com.abdullah.springboot.cruddemo.entity.Role;

public interface RoleDao {

	public Role findRoleByName(String theRoleName);
	
}
