package com.hrmportal.dao;

import org.springframework.stereotype.Repository;

import com.hrmportal.entity.User;

/*
 * @author sakshilavalkar
 */
@Repository
public interface UserDAO {
	User getUser(User user);
	boolean loginUser(User user);
	boolean createUser(User user);
}
