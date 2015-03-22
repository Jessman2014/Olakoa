package com.dahirkanehl.olakoa.users;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
	@Autowired
	private UserDao database;
	
	public User getUser(String username, String password) {
		if(username == null || password == null) return null;
		return database.getUserByUsername(username);
	}
}
