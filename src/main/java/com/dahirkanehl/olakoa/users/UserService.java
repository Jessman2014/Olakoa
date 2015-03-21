package com.dahirkanehl.olakoa.users;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
	@Autowired
	private UserDao database;
	
	public User getUser(String username, String password) {
		if(username == null || password == null) return null;
		for(User user : registeredUsers) {
			if(user.getUsername().equals(username) && user.getPassword().equals(password)) return user;
		}
		return null;
	}
}
